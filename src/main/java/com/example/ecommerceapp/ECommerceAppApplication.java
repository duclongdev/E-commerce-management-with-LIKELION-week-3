package com.example.ecommerceapp;

import com.example.ecommerceapp.entity.Customer;
import com.example.ecommerceapp.model.dto.CustomerDto;
import com.example.ecommerceapp.model.dto.ProductDto;
import com.example.ecommerceapp.sevice.CustomerService;
import com.example.ecommerceapp.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ECommerceAppApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceAppApplication.class, args);
    }

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        if(customerService.findAll().size() == 0) {
            customerService.insert(new CustomerDto("Nguyen Duc Long", "Quang Binh", "0906592672"));
            customerService.insert(new CustomerDto("Nguyen Van A", "TP HCM", "0912345678"));
            customerService.insert(new CustomerDto("Nguyen Thi B", "Ha Noi", "0908070605"));
            customerService.insert(new CustomerDto("Le Thi D", "Da Nang", "0903221122"));
            customerService.insert(new CustomerDto("Tran Van C", "Hai Phong", "0911223344"));

            productService.insert(new ProductDto("ao phong", "ao", "L", 3, new BigDecimal(500000)));
            productService.insert(new ProductDto("ao thun", "ao", "XL", 35, new BigDecimal(55000)));
            productService.insert(new ProductDto("ao phong2", "ao", "S", 12, new BigDecimal(70000)));
            productService.insert(new ProductDto("quan xanh", "quan", "L", 44, new BigDecimal(120000)));
            productService.insert(new ProductDto("quan tay", "quan", "S", 12, new BigDecimal(100000) ));
            productService.insert(new ProductDto("quan den", "quan", "S", 55, new BigDecimal(170000) ));
            productService.insert(new ProductDto("giay fake", "giay", "36", 44, new BigDecimal(120000)));
            productService.insert(new ProductDto("giay real", "giay", "40", 12, new BigDecimal(400000) ));
            productService.insert(new ProductDto("giay nai ki", "giay", "39", 0, new BigDecimal(300000)));
            productService.insert(new ProductDto("giay the thao", "giay", "39", 20, new BigDecimal(170000)));
        }
    }
}
