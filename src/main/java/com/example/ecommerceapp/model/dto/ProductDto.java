package com.example.ecommerceapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private String name_product;
    private String type;
    private String size;
    private int quantity;
    private BigDecimal price;
}
