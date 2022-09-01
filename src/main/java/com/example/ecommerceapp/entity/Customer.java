package com.example.ecommerceapp.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long customer_id;
    private String customer_name;
    private String address;
    private String phone_no;
    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
}
