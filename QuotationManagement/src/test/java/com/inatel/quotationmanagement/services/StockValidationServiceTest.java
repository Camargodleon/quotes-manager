package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("StockValidationService funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class StockValidationServiceTest {

    @Autowired
    StockValidationService stockValidationService;

    private StockDTO stockDTO;
    private Map<String, Double> quotes;

    @BeforeAll
    public void init() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        quotes = new HashMap<>();
        quotes.put("2023-01-31", 10.0);
        quotes.put("2023-01-30", 11.0);
        quotes.put("2023-02-01", 14.0);
        stockDTO = new StockDTO(UUID.randomUUID(), "aapl34", quotes);
    }

    @Test
    @DisplayName("Should throw InvalidStockException")
    public void shouldThrowInvalidStockException() throws JsonProcessingException, InvalidStockException {
        stockDTO.setStockId("invalidID");
        assertThrows(InvalidStockException.class, () -> {
            stockValidationService.verifyStock(stockDTO);
        });
    }

    @Test
    @DisplayName("Should not throw InvalidStockException")
    public void shouldNotThrowInvalidStockException() throws JsonProcessingException, InvalidStockException {
        stockDTO.setStockId("aapl34");
        assertDoesNotThrow(() -> {
            stockValidationService.verifyStock(stockDTO);
        });
    }


}
