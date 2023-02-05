package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.AlreadyRegisteredQuoteException;
import com.inatel.quotationmanagement.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Transactional
    public StockDTO save(StockDTO stockDTO) throws AlreadyRegisteredQuoteException {
        Stock stock = new Stock(stockDTO);
        Stock registered = stockRepository.getByStockId(stock.getStockId());
        if( registered != null){
            if(registered.getQuotes().stream().anyMatch(dbQuote -> stock.getQuotes().stream().anyMatch( newQuote -> newQuote.getDate().equals(dbQuote.getDate())))){
                throw new AlreadyRegisteredQuoteException("A quote has already been registered for this Date.");
            } else {
                registered.getQuotes().addAll(stock.getQuotes());
                return new StockDTO(stockRepository.save(registered));
            }
        } else {
            return new StockDTO(stockRepository.save(stock));
        }

    }

    public StockDTO getStockByStockId(String id){
        Stock stock = stockRepository.getByStockId(id);
        if(stock==null) {
            return null;
        } else {
            return new StockDTO(stockRepository.getByStockId(id));
        }
    }

    public List<StockDTO> getAllStocks(){
        List<StockDTO> stockDTOS = stockRepository.findAll().stream().map(stock -> new StockDTO(stock)).collect(Collectors.toList());
        return stockDTOS;
    }

}
