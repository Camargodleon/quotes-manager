package com.inatel.quotationmanagement.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "STOCK_APP")
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

    @ElementCollection
    @MapKeyColumn(name = "DATE_KEY")
    @Column(name = "value")
    @MapKeyTemporal(TemporalType.DATE)
    private Map<Date, Double> quotes;

    public Stock(String stockId, UUID id) {
        this.stockId = stockId;
        this.id = id;
    }
}
