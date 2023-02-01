package com.inatel.quotationmanagement.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "STOCK")
@Data
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length=36, columnDefinition="char(36)")
    private UUID id;

    @Column(name = "STOCK_ID")
    private String stockId;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    public Stock(String stockId, UUID id) {
        this.stockId = stockId;
        this.id = id;
    }
}
