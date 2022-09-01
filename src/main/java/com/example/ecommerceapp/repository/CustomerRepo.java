package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.entity.Customer;
import com.example.ecommerceapp.model.dto.CustomerDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface CustomerRepo {
    @Select("select * from customer where customer_id = #{customer_id}")
    Customer findById(Long customer_id);
    @Select("select cart_id from customer where customer_id = #{customer_id}")
    int findCartId(Long customer_id);
    @Update("update customer set cart_id = #{customer_id} where customer_id = #{customer_id}")
    int updateCart_id(Long customer_id);
    @Select("select * from customer")
    List<Customer> findAll();

    @Insert("insert into customer (customer_name, address, phone_no) " +
            "values (#{customer_name}, #{address}, #{phone_no})")
    void insert(CustomerDto customerDto);
}
