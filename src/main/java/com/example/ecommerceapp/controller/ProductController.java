package com.example.ecommerceapp.controller;


import com.example.ecommerceapp.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<?> FindByPriceAndCondition( @RequestParam(value = "price", required = true) BigDecimal price,
                                                      @RequestParam(value = "condition", required = true) String condition) {
        return productService.findByPriceAndCondition(price, condition);
    }
}
