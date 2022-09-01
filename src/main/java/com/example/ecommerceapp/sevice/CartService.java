package com.example.ecommerceapp.sevice;

import com.example.ecommerceapp.model.dto.CartItemDto;
import com.example.ecommerceapp.model.request.ProductsRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> insertOrUpdate(Long customer_id, List<ProductsRequest> productsRequests);
    ResponseEntity<?> getAllProduct(Long customer_id, String product_name, Integer offset, Integer limit);
}
