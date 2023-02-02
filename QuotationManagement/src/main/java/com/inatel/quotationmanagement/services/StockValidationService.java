package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockValidationService {


//    public void verifyStock(Stock stock) throws InvalidStockException{
//
//        RestTemplate rest = new RestTemplate();
//        WebClient client = WebClient.create("http://localhost:8080");
//            client.get().uri("/stock").retrieve().bodyToMono(String.class).hasElement().subscribe(hasElement -> {
//                if (!hasElement){
//                    try {
//                        throw new InvalidStockException("Stock isn't properly registered with stock manager.");
//                    } catch (InvalidStockException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    compareRegisteredStocks();
//                }
//            });
//    }

    public void verifyStock(Stock stock) throws InvalidStockException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WebClient client = WebClient.create("http://localhost:8080");
        String json = client.get().uri("/stock").retrieve().bodyToMono(String.class).block();
        List<StockDTO> stockDTOList = objectMapper.readValue(json, new TypeReference<List<StockDTO>>(){});
        if (stockDTOList.isEmpty()){
                throw new InvalidStockException("Stock isn't properly registered with stock manager.");
        } else {
            compareRegisteredStocks(stock, stockDTOList);
        }
    }

    private void compareRegisteredStocks(Stock stock, List<StockDTO> stockDTOList) throws InvalidStockException {
        List<StockDTO> filteredDTOs = stockDTOList.stream().filter(stockDTO -> stockDTO.getId().equals(stock.getStockId())).collect(Collectors.toList());
        if(filteredDTOs.isEmpty()) {
                throw new InvalidStockException("Stock isn't properly registered with stock manager.");
        }
    }


}
