package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.entity.Product;
import com.example.ecommerceapp.model.dto.ProductDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ProductRepo {

    @Select("Select * from PRODUCT")
    List<Product> findAll();
    @Select("select * from product where price < #{price}")
    List<Product> findByPriceAndConditionLessThan(BigDecimal price);
    @Select("select * from product where price > #{price}")
    List<Product> findByPriceAndConditionGreatThan(BigDecimal price);
    @Select("select * from product where price = #{price}")
    List<Product> findByPriceAndConditionEqual(BigDecimal price);
    @Select("select price from product where product_id = #{product_id}")
    BigDecimal selectPriceById(Long product_id);
    @Select("select * from product where product_id = #{product_id}")
    Product findById(Long product_id);
    @Insert("insert into product (name_product, type, size, quantity, price) " +
            "values (#{name_product}, #{type}, #{size}, #{quantity}, #{price})")
    void insert(ProductDto productDto);
    @Select("select quantity from product where product_id = #{product_id}")
    int getQuantity(Long product_id);
    @Update("update product set quantity = #{remainQuantity} where product_id = #{product_id}")
    void updateQuantity(Long product_id, int remainQuantity);
}
