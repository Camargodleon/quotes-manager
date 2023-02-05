package com.inatel.quotationmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.AlreadyRegisteredQuoteException;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import com.inatel.quotationmanagement.services.StockService;
import com.inatel.quotationmanagement.services.StockValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Api(tags = "Stock")
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockValidationService stockValidationService;

    @ApiOperation("Registers new quotes for an existing Stock.")
    @PostMapping("/create")
    public ResponseEntity<StockDTO> postNewStockQuotes(@ApiParam(value = "An object representing a new Stock to be registered")
                                                        @RequestBody StockDTO stockDTO) throws InvalidStockException, JsonProcessingException, AlreadyRegisteredQuoteException {
        stockValidationService.verifyStock(stockDTO);
        StockDTO response = stockService.save(stockDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/get/{id}")
                .buildAndExpand(stockDTO.getStockId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @ApiOperation("Checks for existing Stocks matching the given stock id and returns a List of Stocks and it's registered quotes.")
    @GetMapping("/get/{id}")
    public ResponseEntity<StockDTO> getStockByStockId(@ApiParam(value = "A String representing the id of a Stock", example = "petr4") @PathVariable String id){
        return ResponseEntity.ok(stockService.getStockByStockId(id));
    }

    @ApiOperation("Returns a List of Stocks and it's registered quotes.")
    @GetMapping("/get")
    public ResponseEntity<List<StockDTO>> gellAll(){
        return ResponseEntity.ok(stockService.getAllStocks());
    }
}
