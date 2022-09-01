package com.example.ecommerceapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartItem {

    private Integer quantity_wished;

    private Date add_dated;

    private BigDecimal total_amount;
    @ManyToOne
    @JoinColumn(name="cart_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

}
