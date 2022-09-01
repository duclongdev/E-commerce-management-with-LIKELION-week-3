package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.dto.ProductInCartDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartRepo {
    @Insert("insert into cart (cart_id) values (#{id})")
    int insertByCustomerId(Long id);

    @Select("select pr.product_id, name_product, size, type, price, quantity_wished " +
            "from product pr, cart_item ci " +
            "where pr.product_id = ci.product_id and name_product like '%' || #{product_name} || '%' " +
            "and cart_id = #{customer_id} limit #{limit} offset #{offset}")
    List<ProductInCartDto> getProductByName(Long customer_id, String product_name, Integer offset, Integer limit);
}
