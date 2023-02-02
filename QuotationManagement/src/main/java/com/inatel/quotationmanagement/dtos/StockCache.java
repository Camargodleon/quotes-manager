package com.inatel.quotationmanagement.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "STOCK_CACHE")
public class StockCache {

    @Id
    String id;
    @Column
    String description;
}