package com.example.ecommerceapp.sevice.impl;

import com.example.ecommerceapp.common.TableName;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.NotCreateException;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.NotFoundException;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.OutOfStockException;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.QuantityException;
import com.example.ecommerceapp.model.ResponseObject;
import com.example.ecommerceapp.model.dto.CartItemDto;
import com.example.ecommerceapp.model.dto.ProductInCartDto;
import com.example.ecommerceapp.model.request.ProductsRequest;
import com.example.ecommerceapp.repository.CartRepo;
import com.example.ecommerceapp.sevice.CartItemService;
import com.example.ecommerceapp.sevice.CartService;
import com.example.ecommerceapp.sevice.CustomerService;
import com.example.ecommerceapp.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartRepo cartRepo;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;


    private Long createCart(Long customer_id) {
        if (cartRepo.insertByCustomerId(customer_id) != 0)
            return customer_id;
        throw new NotCreateException(String.format("Duplicate %s id violates unique constraint", TableName.Name.CART));
    }

    public ResponseEntity<?> insert(Long customer_id, List<ProductsRequest> productsRequests) {
        createCart(customer_id);
        customerService.updateCartId(customer_id);
        List<CartItemDto> cartItemDtos = cartItemService.mapperToCartItem(productsRequests, customer_id);
        for (CartItemDto cartItemDto : cartItemDtos) {
            productService.updateQuantity(cartItemDto.getProduct_id(), cartItemDto.getQuantity_wished());
            cartItemService.insert(cartItemDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK, "Create cart successfully", cartItemService.findAll(customer_id)));
    }
    private List<ProductsRequest> handlerConflict(List<ProductsRequest> productsRequests) {
        int size = productsRequests.size();
        if (size == 1) return productsRequests;
        for (int i = 0; i < size - 1; i++) {
            ProductsRequest curProductRequest = productsRequests.get(i);
            for (int j = i + 1; j < size; j++) {
                ProductsRequest productsRequest = productsRequests.get(j);
                if (curProductRequest.getProduct_id() == productsRequest.getProduct_id()) {
                    int total_quantity_wished = curProductRequest.getQuantity_wished() +
                            productsRequest.getQuantity_wished();
                    curProductRequest.setQuantity_wished(total_quantity_wished);
                    productsRequests.remove(j);
                    j--;
                    size--;
                }
            }
        }
        return productsRequests;
    }

    private ResponseEntity<?> update(Long customer_id, List<ProductsRequest> productsRequests) {
        List<CartItemDto> cartItemDtoList = cartItemService.mapperToCartItem(productsRequests, customer_id);
        for (CartItemDto cartItemDto : cartItemDtoList) {
            productService.updateQuantity(cartItemDto.getProduct_id(), cartItemDto.getQuantity_wished());
            cartItemService.update(cartItemDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK, "Update cart successfully", cartItemService.findAll(customer_id)));
    }

    @Override
    public ResponseEntity<?> insertOrUpdate(Long customer_id, List<ProductsRequest> productsRequests) {
        productsRequests = handlerInvalid(customer_id, productsRequests);
        if (customerService.isCartExists(customer_id)) {
            return update(customer_id, productsRequests);
        }
        return insert(customer_id, productsRequests);
    }

    private List<ProductsRequest> handlerInvalid(Long customer_id, List<ProductsRequest> productsRequests) {
        checkInputInvalid(customer_id, productsRequests);
        productsRequests = handlerConflict(productsRequests);
        checkInvalidQuantityOfProduct(productsRequests);
        return productsRequests;
    }

    private void checkInvalidQuantityOfProduct(List<ProductsRequest> productsRequests) {
        for(ProductsRequest productsRequest : productsRequests){
            int quantityOfProduct = productService.getQuantity(productsRequest.getProduct_id());
            if(productsRequest.getQuantity_wished() > quantityOfProduct)
                throw new OutOfStockException(String.valueOf(productsRequest.getProduct_id()),
                        String.valueOf(quantityOfProduct));
        }
    }

    @Override
    public ResponseEntity<?> getAllProduct(Long customer_id, String product_name, Integer offset, Integer limit) {
        customerService.checkExistsCustomer(customer_id);
        if (!customerService.isCartExists(customer_id))
            throw new NotFoundException(TableName.Name.CART);
        List<ProductInCartDto> result = cartRepo.getProductByName(customer_id, product_name, offset, limit);
        if(result.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK, "Show product success fully",result ));
        throw new NotFoundException(TableName.Name.PRODUCT);
    }
    private void checkInputInvalid(Long customer_id, List<ProductsRequest> productsRequests) {
        customerService.checkExistsCustomer(customer_id);
        for (ProductsRequest productsRequest : productsRequests) {
            productService.checkExistsProduct(productsRequest.getProduct_id());
            if (productsRequest.getQuantity_wished() <= 0)
                throw new QuantityException(TableName.Name.PRODUCT, String.valueOf(productsRequest.getProduct_id()));
        }
    }
}
