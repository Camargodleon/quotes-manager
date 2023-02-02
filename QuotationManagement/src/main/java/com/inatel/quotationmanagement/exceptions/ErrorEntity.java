package com.inatel.quotationmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorEntity {

    private String message;
    private Integer code;
}
