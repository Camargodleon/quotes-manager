package com.inatel.quotationmanagement.entities.dtos;

import com.inatel.quotationmanagement.entities.Quote;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuoteDto {


    private UUID id;

    private String stockId;

    List<Quote> quoteList;


}
