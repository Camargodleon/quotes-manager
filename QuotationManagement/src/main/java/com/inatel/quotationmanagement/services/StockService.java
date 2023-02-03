package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Transactional
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getStockByStockId(String id){
        return stockRepository.getByStockId(id);
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

}
