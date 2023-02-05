package com.inatel.quotationmanagement.entities;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Quote", description = "Represents a Quote.")
public class Quote {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quote_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "quote_value")
    private Double value;

    public  Quote(Date date, Double value){
        this.date = date;
        this.value = value;
    }

    public String getStringDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
    }

    public Date getDate() {

        return this.date;
    }
}
