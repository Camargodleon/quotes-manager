package com.inatel.quotationmanagement.dtos;

import com.inatel.quotationmanagement.entities.Quote;
import com.inatel.quotationmanagement.entities.Stock;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {


    private UUID id;

    private String stockId;

    private Map<String, Double> quotes;

    public StockDTO(Stock stock) {
        this.setId(stock.getId());
        this.setStockId(stock.getStockId());
        this.setQuotes(stock.getQuotes().stream().collect(Collectors.toMap(Quote::getStringDate, Quote::getValue)));
    }
}
