package com.inatel.quotationmanagement.exceptions;

import com.inatel.quotationmanagement.entities.Stock;

public class InvalidStockException extends Exception {
    public InvalidStockException(String s) {
        super(s);
    }
}
