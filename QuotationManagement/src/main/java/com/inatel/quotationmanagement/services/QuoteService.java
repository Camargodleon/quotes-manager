package com.inatel.quotationmanagement.services;


import com.inatel.quotationmanagement.entities.Quote;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.entities.dtos.QuoteDto;
import com.inatel.quotationmanagement.repositories.QuoteRepository;
import com.inatel.quotationmanagement.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    QuoteRepository quoteRepository;

    public List<Quote> save(QuoteDto dto) {
        Stock stock = new Stock(dto.getStockId(), dto.getId());
        List<Quote> quotes = dto.getQuoteList();
        quotes.stream().forEach(quote -> quote.setStock(stock));
        return quoteRepository.saveAll(quotes);
    }
}
