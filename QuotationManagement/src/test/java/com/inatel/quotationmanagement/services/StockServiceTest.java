package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.repositories.StockRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("StockRepository funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@Order(8)
public class StockServiceTest {


    @Autowired
    private StockService stockService;
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
        assertNotNull(stockService.save(stock));
    }

    @Test
    @DisplayName("Return empty list when ask for non existent stock")
    public void returnNullWhenAskingForNonExistentStock(){
        List<Stock> stockList = stockService.getStockByStockId("invalidID");
        assertThat(stockList).isEmpty();
    }

    @Test
    @DisplayName("Return Stock list when using valid stockId")
    public void returnStockWhenAskingValidStockID(){
        List<Stock> stockList = stockService.getStockByStockId("petr4");
        assertThat(stockList).allSatisfy(stock -> stock.getStockId().equals("petr4"));
    }


    @Test
    @DisplayName("Return List<Stock> on getAllStocks() call")
    public void returnStockList(){
        List<Stock> stock = stockService.getAllStocks();
        assertTrue(!stock.isEmpty());
    }
}
