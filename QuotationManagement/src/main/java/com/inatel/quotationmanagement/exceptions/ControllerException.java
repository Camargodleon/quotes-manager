package com.inatel.quotationmanagement.exceptions;

import com.inatel.quotationmanagement.configurations.RegisterHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice()
public class ControllerException extends ResponseEntityExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerException.class);
    @ExceptionHandler(value = InvalidStockException.class)
    public ResponseEntity<ErrorEntity> handleInvalidStockException(InvalidStockException ex){
        LOGGER.error("Caught InvalidStockException.");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorEntity(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}
