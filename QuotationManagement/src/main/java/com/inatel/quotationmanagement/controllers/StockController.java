package com.inatel.quotationmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import com.inatel.quotationmanagement.services.StockService;
import com.inatel.quotationmanagement.services.StockValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockValidationService stockValidationService;

    @PostMapping("/create")
    public ResponseEntity<Stock> postNewStockQuotes(@RequestBody Stock stock) throws InvalidStockException, JsonProcessingException {
        stockValidationService.verifyStock(stock);
        Stock response = stockService.save(stock);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/get/{id}")
                .buildAndExpand(stock.getStockId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Stock> getStockByStockId(@PathVariable String id){
        return ResponseEntity.ok(stockService.getStockByStockId(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Stock>> gellAll(){

        return ResponseEntity.ok(stockService.getAllStocks());
    }

}
