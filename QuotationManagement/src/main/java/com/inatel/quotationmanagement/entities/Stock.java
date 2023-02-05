package com.inatel.quotationmanagement.entities;


import com.inatel.quotationmanagement.dtos.StockDTO;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "stock_app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Stock", description = "Represents a stock and it's Quotes.")
public class Stock {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length=36, columnDefinition="CHAR(36)")
    @ApiModelProperty(value = "id", example = "c01cede4-cd45-11eb-b8bc-0242ac130003", access = "private", dataType = "UUID", notes = "Unique ID for this register.")
    @NotNull
    private UUID id;

    @Column(name = "stock_id", columnDefinition = "VARCHAR(255)", unique = true)
    @ApiModelProperty(name = "Stock ID", dataType = "String", access = "private", example = "petr4", notes = "String representing a stock.")
    private String stockId;


    @ApiModelProperty(notes = "Quotes Registered to this Stock", value = "quotes",name = "Quotes", dataType = "Quote", access = "private")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", foreignKey = @ForeignKey(name = "stock_id"))
    private List<Quote> quotes;

    public Stock(StockDTO stockDTO){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.setStockId(stockDTO.getStockId());
        List<Quote> quotes = stockDTO.getQuotes().entrySet().stream().map(entry -> {
            try {
                return new Quote(dateFormat.parse(entry.getKey()), entry.getValue());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        this.setQuotes(quotes);
        this.setId(stockDTO.getId());
    }

}
