package com.example.ecommerceapp.sevice;

import com.example.ecommerceapp.model.dto.CartItemDto;
import com.example.ecommerceapp.model.request.ProductsRequest;

import java.util.List;

public interface CartItemService {
    CartItemDto insert(CartItemDto cartItemDto);
    List<CartItemDto> mapperToCartItem(List<ProductsRequest> productsRequests, Long customer_id);
    void update(CartItemDto cartItemDto);
    List<CartItemDto> findAll(Long cart_id);
}
