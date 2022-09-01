package com.example.ecommerceapp.common.erorrException;

import com.example.ecommerceapp.common.erorrException.ErrorExceptionCustomize.*;
import com.example.ecommerceapp.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlerNotFoundException(NotFoundException ex){
        return new ResponseError(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalException.class)
    public ResponseError handlerIllegalArgumentException(IllegalException ex){
        return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotCreateException.class)
    public ResponseError handlerNotCreateException (NotCreateException ex){
        return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuantityException.class)
    public ResponseError handlerQuantityException(QuantityException ex){
        return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OutOfStockException.class)
    public ResponseError handlerOutOfStockException(OutOfStockException ex){
        return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
