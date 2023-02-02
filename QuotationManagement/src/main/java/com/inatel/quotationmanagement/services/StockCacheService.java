package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.entities.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockCacheService {


    private static final Logger LOGGER = LoggerFactory.getLogger(StockCacheService.class);

    @Cacheable("stock")
    public List<StockCache> findAll() throws JsonProcessingException {
        LOGGER.info("Fetching data from stock-manager service.");
        ObjectMapper objectMapper = new ObjectMapper();
        WebClient client = WebClient.create("http://localhost:8080");
        String json = client.get().uri("/stock").retrieve().bodyToMono(String.class).block();
        List<StockCache> stockCacheList = objectMapper.readValue(json, new TypeReference<List<StockCache>>(){});
        return stockCacheList;
    }

    public boolean verifyStockCache(Stock stock) throws JsonProcessingException {
        List<StockCache> stockCacheList = findAll();
        if(stockCacheList.stream().filter(stockCache -> stockCache.getId().equals(stock.getStockId())).collect(Collectors.toList()).isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    @CacheEvict("stock")
    public void clearCache() {
        LOGGER.info("Evicted cached data.");
    }
}
