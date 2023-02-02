package com.inatel.quotationmanagement.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice()
public class ControllerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidStockException.class)
    public ResponseEntity<ErrorEntity> handleInvalidStockException(InvalidStockException ex){
        return ResponseEntity.badRequest().body(new ErrorEntity(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}
