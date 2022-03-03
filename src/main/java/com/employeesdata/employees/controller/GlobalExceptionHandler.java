package com.employeesdata.employees.controller;


import com.employeesdata.employees.exception.BusinessException;
import com.employeesdata.employees.exception.ErrorDetails;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException nsex,WebRequest req) {
        ErrorDetails ed1 = new ErrorDetails(701, nsex.getMessage());
        return new ResponseEntity<>(ed1, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    protected ResponseEntity<?> handleException(){
        ErrorDetails err = new ErrorDetails();
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest req){
        ErrorDetails err = null;
        if(ex.getMessage() == "601"){
            err = new ErrorDetails(601,"Name shouldn't be null or Empty");
        }
        else if(ex.getMessage() == "602"){
           err = new ErrorDetails(602,"Name shouldn't be less than 3 characters");
        }
        else if(ex.getMessage() == "603"){
            err = new ErrorDetails(603,"Name should be less than 16 characters");
        }
        else if(ex.getMessage() == "604"){
            err = new ErrorDetails(604,"Email shouldn't be blank or empty");
        }
        else if(ex.getMessage() == "605"){
            err = new ErrorDetails(605,"Enter Valid Email");
        }
        else if(ex.getMessage() == "606"){
            err = new ErrorDetails(606,"Mobile Number shouldn't Empty");
        }
        else if(ex.getMessage() == "607"){
            err = new ErrorDetails(607,"Mobile Number should 10 Digits long");
        }
        else if(ex.getMessage() == "608"){
            err = new ErrorDetails(608,"Enter Valid Mobile Number");
        }
        else if(ex.getMessage() == "609"){
            err = new ErrorDetails(609,"Salary Shouldn't be Empty");
        }
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, HttpServletRequest request, WebRequest req) {
        ErrorDetails ed1 = new ErrorDetails(701, ex.getMessage());
        return new ResponseEntity<>(ed1, HttpStatus.SERVICE_UNAVAILABLE);
    }





    }




