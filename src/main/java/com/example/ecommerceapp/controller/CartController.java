package com.example.ecommerceapp.controller;


import com.example.ecommerceapp.model.request.ProductsRequest;
import com.example.ecommerceapp.sevice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("{customer_id}")
    public ResponseEntity<?> insert(@PathVariable Long customer_id,
                                    @RequestBody  List<ProductsRequest> productsRequests){
        return cartService.insertOrUpdate(customer_id, productsRequests);
    }
    @GetMapping
    public ResponseEntity<?> getAllProductByNameInCart(
            @RequestParam(name = "customer-id", required = true) Long customer_id,
            @RequestParam(name = "product-name", required = true) String product_name,
            @RequestParam(name = "offset", required = true) Integer offset,
            @RequestParam(name = "limit", required = true) Integer limit){
        return cartService.getAllProduct(customer_id, product_name, offset, limit);
    }
}
