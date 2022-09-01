package com.example.ecommerceapp.model.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDto {
    private Long product_id;
    private Long cart_id;
    private int quantity_wished;
    private BigDecimal total_amount;
}
