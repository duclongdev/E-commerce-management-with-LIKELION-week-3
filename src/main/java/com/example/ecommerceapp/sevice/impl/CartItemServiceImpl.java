package com.example.ecommerceapp.sevice.impl;

import com.example.ecommerceapp.model.dto.CartItemDto;
import com.example.ecommerceapp.model.request.ProductsRequest;
import com.example.ecommerceapp.repository.CartItemRepo;
import com.example.ecommerceapp.sevice.CartItemService;
import com.example.ecommerceapp.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Resource
    CartItemRepo cartItemRepo;
    @Autowired
    ProductService productService;

    @Override
    public CartItemDto insert(CartItemDto cartItemDto) {
        cartItemRepo.insert(cartItemDto);
        return cartItemDto;
    }
    @Override
    public List<CartItemDto> mapperToCartItem(List<ProductsRequest> productsRequests, Long customer_id) {
        List<CartItemDto> result = new ArrayList<>();
        for (int i = 0; i < productsRequests.size(); i++) {
            CartItemDto temp = map(productsRequests.get(i), customer_id);
            result.add(temp);
        }
        return result;
    }
    private CartItemDto map(ProductsRequest productsRequest, Long customer_id) {
        CartItemDto temp = new CartItemDto();
        temp.setCart_id(customer_id);
        temp.setProduct_id(productsRequest.getProduct_id());
        temp.setQuantity_wished(productsRequest.getQuantity_wished());
        temp.setTotal_amount(calculateTotalPrice(temp.getProduct_id(), temp.getQuantity_wished()));
        return temp;
    }
    @Override
    public void update(CartItemDto cartItemDto) {
        Long product_id = cartItemDto.getProduct_id();
        Long cart_id = cartItemDto.getCart_id();
        int quantity = cartItemDto.getQuantity_wished();
        if (isProductExists(product_id, cart_id))
            mergeToCartItem(product_id, cart_id, quantity);
        else
            insert(cartItemDto);
    }
    @Override
    public List<CartItemDto> findAll(Long cart_id) {
        return cartItemRepo.findAll(cart_id);
    }
    private void mergeToCartItem(Long product_id, Long cart_id,int quantity) {
        int quantityInDb = cartItemRepo.getQuantityOfProduct(product_id, cart_id);
        int totalQuantity = quantityInDb + quantity;
        BigDecimal totalPriceInCart = calculateTotalPrice(product_id, totalQuantity);
        cartItemRepo.updateQuantity(totalQuantity, cart_id, product_id);
        cartItemRepo.updateTotalPrice(totalPriceInCart, cart_id, product_id);
    }
    private boolean isProductExists(Long product_id, Long cart_id) {
        if (cartItemRepo.isProductExists(product_id, cart_id) != null)
            return true;
        return false;
    }
    private BigDecimal calculateTotalPrice(Long product_id, int quantity_wished) {
        BigDecimal price = productService.getPriceById(product_id);
        return price.multiply(BigDecimal.valueOf(quantity_wished));
    }
}
