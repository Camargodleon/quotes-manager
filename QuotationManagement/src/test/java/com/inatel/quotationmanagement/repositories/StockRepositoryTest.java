package com.inatel.quotationmanagement.repositories;

import com.inatel.quotationmanagement.entities.Stock;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StockRepository funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@Order(2)
public class StockRepositoryTest{



    @Autowired
    private StockRepository stockRepository;
    private Stock stock;
    private Map<Date, Double> quotes;

    @BeforeAll
    public void init() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        quotes = new HashMap<>();
        quotes.put(formatter.parse("2023-01-31"), 10.0);
        quotes.put(formatter.parse("2023-01-30"), 11.0);
        quotes.put(formatter.parse("2023-02-01"), 14.0);
        stock = new Stock(UUID.randomUUID(), "petr4", quotes);
    }

    @Test
    @DisplayName("Return Stock Entity")
    public void returnEntityOnSave(){

        assertNotNull(stockRepository.save(stock));
    }

    @Test
    @DisplayName("Return empty list when ask for non existent stock")
    public void returnNullWhenAskingForNonExistentStock(){
        List<Stock> stockList = stockRepository.getByStockId("invalidID");
        assertThat(stockList).isEmpty();
    }

    @Test
    @DisplayName("Return Stock when using valid stockId")
    public void returnStockWhenAskingValidStockID(){
        List<Stock> stockList = stockRepository.getByStockId("petr4");
        assertThat(stockList).allSatisfy(stock -> stock.getStockId().equals("petr4"));
    }


    @Test
    @DisplayName("Return List<Stock> on findAll() call")
    public void returnStockList(){
        List<Stock> stock = stockRepository.findAll();
        assertTrue(!stock.isEmpty());
    }



}
