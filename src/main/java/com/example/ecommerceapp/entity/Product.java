package com.example.ecommerceapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Product {
    @Id
    @GeneratedValue
    private Long product_id;
    private String name_product;
    private String type;
    private String size;
    private int quantity;
    private BigDecimal price;

    @OneToMany(mappedBy = "product",  fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CartItem>  cartItems;

}
