package com.g.miniproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

    private Long fieldValue;
    private String fieldName;

    private String resourceName;
    public EmployeeNotFoundException(String resourceName, String fieldName,  Long fieldValue){
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));

        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
