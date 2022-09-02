package com.example.ecommerceapp.sevice.impl;

import com.example.ecommerceapp.common.TableName;
import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.NotFoundException;
import com.example.ecommerceapp.entity.Customer;
import com.example.ecommerceapp.model.dto.CustomerDto;
import com.example.ecommerceapp.repository.CustomerRepo;
import com.example.ecommerceapp.sevice.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    CustomerRepo customerRepo;

    public boolean checkExistsCustomer(Long customer_id) {
        if (customerRepo.findById(customer_id) != null)
            return true;
        throw new NotFoundException(TableName.Name.CUSTOMER);
    }
    @Override
    public boolean isCartExists(Long customer_id) {
       try {
           customerRepo.findCartId(customer_id);
           return true;
       }catch(Exception e) {
            return false;
        }
    }
    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    @Override
    public void updateCartId(Long customer_id) {
        customerRepo.updateCart_id(customer_id);
    }
    @Override
    public void insert(CustomerDto customerDto) {
        customerRepo.insert(customerDto);
    }
}
