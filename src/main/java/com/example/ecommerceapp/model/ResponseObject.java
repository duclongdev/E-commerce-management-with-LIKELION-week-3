package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseObject {
    private HttpStatus httpStatus;
    private String message;
    private Object data;
}
