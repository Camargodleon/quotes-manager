package com.inatel.quotationmanagement.controllers;

import com.inatel.quotationmanagement.entities.Quote;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.entities.dtos.QuoteDto;
import com.inatel.quotationmanagement.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/create")
    public ResponseEntity<List<Quote>> postNewQuote(@RequestBody QuoteDto dto){
        List<Quote> response = quoteService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/get/{id}")
                .buildAndExpand(response.get(0).getStock().getStockId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable String id) {

    }

    @GetMapping("/list")
    public ResponseEntity<List<Stock>> getAllStocks() {

    }

}
