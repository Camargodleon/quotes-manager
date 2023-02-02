package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.dtos.StockCacheRepository;
import com.inatel.quotationmanagement.entities.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockCacheService {


    @Autowired
    private StockCacheRepository stockCacheRepository;

    @Transactional
    public StockCache save(StockCache stockCache){
        return stockCacheRepository.save(stockCache);
    }

    public List<StockCache> findAll(){
        return stockCacheRepository.findAll();
    }

    public Optional<StockCache> findByid(String id){
        return stockCacheRepository.findById(id);
    }

    public boolean verifyStockCache(Stock stock) throws JsonProcessingException {
        List<StockCache> stockCacheList = stockCacheRepository.findAll();
        if(stockCacheList.isEmpty()){
            getStockFromService();
            stockCacheList = stockCacheRepository.findAll();
        }
        if(stockCacheList.stream().filter(stockCache -> stockCache.getId().equals(stock.getStockId())).collect(Collectors.toList()).isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public void getStockFromService() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WebClient client = WebClient.create("http://localhost:8080");
        String json = client.get().uri("/stock").retrieve().bodyToMono(String.class).block();
        List<StockCache> stockCacheList = objectMapper.readValue(json, new TypeReference<List<StockCache>>(){});
        stockCacheRepository.saveAll(stockCacheList);
    }

    @Transactional
    public void clearCache() {
        stockCacheRepository.deleteAll();
    }
}
