package com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String product_id, String quantity){
        super(String.format("Out of stock! quantity wished of product with id %s must be less than %s",
                product_id, quantity));
    }
}
