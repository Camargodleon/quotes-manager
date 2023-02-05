package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.AlreadyRegisteredQuoteException;
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
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("StockRepository funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockServiceTest {


    @Autowired
    private StockService stockService;
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
    @Order(1)
    public void returnEntityOnSave() throws AlreadyRegisteredQuoteException {
        assertNotNull(stockService.save(stockDTO));
    }

    @Test
    @DisplayName("Return AlreadyRegisteredQuoteException when saving Quotes for same stock and date")
    @Order(2)
    public void shouldReturnAlreadyRegisteredQuoteException() throws AlreadyRegisteredQuoteException {

        assertThrows(AlreadyRegisteredQuoteException.class, () -> stockService.save(stockDTO));
    }

    @Test
    @DisplayName("Return null when ask for non existent stock")
    public void returnNullWhenAskingForNonExistentStock(){
        StockDTO dto = stockService.getStockByStockId("invalidID");
        assertNull(dto);
    }

    @Test
    @DisplayName("Return Stock register when using valid stockId")
    public void returnStockWhenAskingValidStockID(){
        StockDTO dto = stockService.getStockByStockId("petr4");
        assertNotNull(dto);
    }


    @Test
    @DisplayName("Return List<StockDTO> on getAllStocks() call")
    public void returnStockList(){
        List<StockDTO> stock = stockService.getAllStocks();
        assertTrue(!stock.isEmpty());
    }
}
