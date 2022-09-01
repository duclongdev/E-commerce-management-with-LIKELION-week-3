package com.example.ecommerceapp.sevice;

import com.example.ecommerceapp.model.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;


public interface ProductService {
    ResponseEntity<?> findByPriceAndCondition(BigDecimal price, String condition);
    BigDecimal getPriceById(Long product_id);
    boolean checkExistsProduct(Long product_id);
    void insert(ProductDto productDto);

    int getQuantity(Long product_id);

    void updateQuantity(Long product_id, int quantity_wished);
}
