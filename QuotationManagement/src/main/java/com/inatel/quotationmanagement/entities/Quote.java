package com.inatel.quotationmanagement.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "QUOTE")
@Data
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "VALUE")
    private Double value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

}
