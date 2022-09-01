package com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String tableName){
        super(String.format("%s not exists", tableName));
    }
}
