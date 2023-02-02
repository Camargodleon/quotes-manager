package com.inatel.quotationmanagement.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "DATE")
    private Date date;

    @Column(name = "VALUE")
    private Double value;

}
