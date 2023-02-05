package com.inatel.quotationmanagement.repositories;

import com.inatel.quotationmanagement.dtos.StockDTO;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StockRepository funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class StockRepositoryTest{



    @Autowired
    private StockRepository stockRepository;
    private Stock stock;

    private StockDTO stockDTO;
    private Map<String, Double> quotes;

    @BeforeAll
    public void init() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        quotes = new HashMap<>();
        quotes.put("2023-01-31", 10.0);
        quotes.put("2023-01-30", 11.0);
        quotes.put("2023-02-01", 14.0);
        stockDTO = new StockDTO(UUID.randomUUID(), "petr4", quotes);
        stock = new Stock(stockDTO);
    }

    @Test
    @DisplayName("Return Stock Entity")
    public void returnEntityOnSave(){
        stock.setStockId("validID");
        assertNotNull(stockRepository.save(stock));
    }

    @Test
    @DisplayName("Return null when ask for non existent stock")
    public void returnNullWhenAskingForNonExistentStock(){
        Stock result = stockRepository.getByStockId("invalidID");
        assertNull(result);
    }

    @Test
    @DisplayName("Return Stock when using valid stockId")
    public void returnStockWhenAskingValidStockID(){

        assertNotNull(stockRepository.getByStockId("petr4"));
    }


    @Test
    @DisplayName("Return List<Stock> on findAll() call")
    public void returnStockList(){
        List<Stock> stock = stockRepository.findAll();
        assertTrue(!stock.isEmpty());
    }



}
