package com.inatel.quotationmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Host {

    private String host;
    private int port;
}
