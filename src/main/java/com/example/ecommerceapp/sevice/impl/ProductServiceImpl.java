package com.example.ecommerceapp.sevice.impl;

import com.example.ecommerceapp.common.TableName;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.IllegalException;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.NotFoundException;
import com.example.ecommerceapp.entity.Product;
import com.example.ecommerceapp.model.ResponseObject;
import com.example.ecommerceapp.model.dto.ProductDto;
import com.example.ecommerceapp.repository.ProductRepo;
import com.example.ecommerceapp.sevice.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<?> findByPriceAndCondition(BigDecimal price, String condition) {
        checkPrice(price);
        List<Product> products = new ArrayList<>();
        switch (condition){
            case "LESS_THAN":
                products = productRepo.findByPriceAndConditionLessThan(price);
                break;
            case "GREATER_THAN":
                products = productRepo.findByPriceAndConditionGreatThan(price);
                break;
            case "EQUAL":
                products = productRepo.findByPriceAndConditionEqual(price);
                break;
            default:
                throw new IllegalException(String.format("Condition = %s", condition));
        }
        if(products.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK,
                    "Query data successfully", products));
        throw new NotFoundException(TableName.Name.PRODUCT);
    }

    private void checkPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) == 1)
            return;
        throw new IllegalException(String.format("price = %s", String.valueOf(price)));
    }

    @Override
    public BigDecimal getPriceById(Long product_id) {
        return productRepo.selectPriceById(product_id);
    }
    @Override
    public boolean checkExistsProduct(Long product_id) {
        if(productRepo.findById(product_id) != null)
            return true;
        throw new NotFoundException(String.format("%s by id = %s",TableName.Name.PRODUCT, String.valueOf(product_id)));
    }

    @Override
    public void insert(ProductDto productDto) {
        productRepo.insert(productDto);
    }

    @Override
    public int getQuantity(Long product_id) {
        return productRepo.getQuantity(product_id);
    }

    @Override
    public void updateQuantity(Long product_id, int quantity_wished) {
        int quantity = productRepo.getQuantity(product_id);
        int remainQuantity = quantity - quantity_wished;
        productRepo.updateQuantity(product_id,remainQuantity);
    }


}
