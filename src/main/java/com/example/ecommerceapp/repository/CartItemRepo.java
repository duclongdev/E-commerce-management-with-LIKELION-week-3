package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.dto.CartItemDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartItemRepo  {
    @Insert("insert into cart_item (cart_id, product_id, quantity_wished, total_amount)" +
            " values (#{cart_id}, #{product_id}, #{quantity_wished}, #{total_amount})")
    int insert(CartItemDto cartItemDto);
    @Select("select * from cart_item where product_id = #{product_id} and cart_id = #{cart_id}")
    CartItemDto isProductExists(Long product_id, Long cart_id);
    @Select("select quantity_wished from cart_item where cart_id = #{cart_id} and product_id = #{product_id}")
    int getQuantityOfProduct(Long product_id, Long cart_id);
    @Update("update cart_item set quantity_wished = #{total_quantity} where cart_id = #{cart_id} and product_id = #{product_id}")
    void updateQuantity(int total_quantity, Long cart_id, Long product_id);
    @Update("update cart_item set total_amount = #{totalAmount} where cart_id = #{cart_id} " +
            "and product_id = #{product_id}")
    void updateTotalPrice(BigDecimal totalAmount, Long cart_id, Long product_id);

    @Select("select * from cart_item where cart_id = #{cart_id}")
    List<CartItemDto> findAll(Long cart_id);
}
