package com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize;

public class IllegalException extends RuntimeException{
    public IllegalException(String argument){
        super(String.format("%s argument invalid", argument));
    }
}
