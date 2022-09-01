package com.example.ecommerceapp.model.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductInCartDto {
    private Long product_id;
    private String name_product;
    private String type;
    private String size;
    private BigDecimal price;
    private int quantity_wished;
}
