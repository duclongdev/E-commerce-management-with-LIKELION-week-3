package com.example.ecommerceapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long cart_id;
    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartItem> cartItems;
}
