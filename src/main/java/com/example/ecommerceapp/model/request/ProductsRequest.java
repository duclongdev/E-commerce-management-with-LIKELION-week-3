package com.example.ecommerceapp.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class ProductsRequest {
    @NotNull
    private Long product_id;
    @NotNull
    @Min(1)
    private int quantity_wished;
}
