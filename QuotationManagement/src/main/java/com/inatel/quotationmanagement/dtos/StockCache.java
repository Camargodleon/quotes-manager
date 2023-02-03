package com.inatel.quotationmanagement.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class StockCache {


    private String id;

    private String description;
}
