package com.example.ecommerceapp.sevice;

import com.example.ecommerceapp.entity.Customer;
import com.example.ecommerceapp.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    boolean checkExistsCustomer(Long customer_id);
    boolean isCartExists (Long customer_id);
    List<Customer> findAll();
    void updateCartId(Long customer_id);

    void insert(CustomerDto customerDto);
}
