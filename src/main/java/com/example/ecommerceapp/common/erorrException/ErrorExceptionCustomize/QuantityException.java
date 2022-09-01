package com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize;

public class QuantityException extends RuntimeException{
    public QuantityException(String tbName,String product_id){
        super(String.format("Quantity of %s with id = %s must be greater than 0",tbName, product_id));
    }
}
