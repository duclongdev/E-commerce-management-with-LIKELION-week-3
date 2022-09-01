package com.example.ecommerceapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private String customer_name;
    private String address;
    private String phone_no;
}
