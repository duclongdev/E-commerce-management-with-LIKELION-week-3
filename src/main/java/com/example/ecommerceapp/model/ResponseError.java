package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ResponseError {
    private HttpStatus httpStatus;
    private Object data;
}
