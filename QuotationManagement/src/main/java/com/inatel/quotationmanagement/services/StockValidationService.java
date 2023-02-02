package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockValidationService {

    @Autowired
    private StockCacheService stockCacheService;

    public void verifyStock(Stock stock) throws InvalidStockException, JsonProcessingException {
        if (!stockCacheService.verifyStockCache(stock)){
                throw new InvalidStockException("Stock isn't properly registered with stock manager.");
        }
    }
}
