package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.quotationmanagement.dtos.StockCache;
import com.inatel.quotationmanagement.entities.Stock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StockCacheService funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@Order(5)
public class StockCacheServiceTest {

    @Autowired
    private StockCacheService stockCacheService;

    @Autowired
    private CacheManager cacheManager;

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
    @DisplayName("Returns a list of StockCache")
    public void returnsListOfStockCache() throws JsonProcessingException {
        assertNotNull(stockCacheService.findAll());
    }

    @Test
    @DisplayName("Validates valid stock")
    public void validatesValidStock() throws JsonProcessingException {
        assertTrue(stockCacheService.verifyStockCache(stock));
    }


    @Test
    @DisplayName("Invalidates invalid stock")
    public void invalidatesInvalidStock() throws JsonProcessingException {
        stock.setStockId("invalidID");
        assertFalse(stockCacheService.verifyStockCache(stock));
    }

}
