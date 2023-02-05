package com.inatel.quotationmanagement.controllers;

import com.inatel.quotationmanagement.dtos.StockDTO;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@DisplayName("StockController funcionality tests.")
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    private Map<String, Double> quotes;

    private StockDTO stockDTO;


    @BeforeAll
    public void init() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        quotes = new HashMap<>();
        quotes.put("2023-01-31", 10.0);
        quotes.put("2023-01-30", 11.0);
        quotes.put("2023-02-01", 14.0);
        stockDTO = new StockDTO(UUID.randomUUID(), "petr4", quotes);
    }


    @Test
    @DisplayName("Return list on stock/get endpoint")
    @Order(1)
    public void returnListOnGetEndpoint(){
        webTestClient.get().uri("stock/get").exchange().expectStatus().isOk().expectBodyList(StockDTO.class);
    }

    @Test
    @DisplayName("Return one entry on stock/get/{id} endpoint")
    @Order(2)
    public void returnListOnGetAllEndpoint(){
        webTestClient.get().uri("/stock/get/petr4").exchange().expectStatus().isOk().expectBody(StockDTO.class);
    }

    @Test
    @DisplayName("Throw exception when posting unregistered stock")
    @Order(3)
    public void throwExceptionOnUnregisretedStock(){
        stockDTO.setStockId("ABEV3");
        webTestClient.post().uri("stock/create").body(Mono.just(stockDTO), StockDTO.class).exchange().expectStatus().is4xxClientError().expectBody(InvalidStockException.class);
    }

    @Test
    @DisplayName("Should persist registered stock")
    @Order(4)
    public void persistRegisteredStock(){
        stockDTO.setStockId("petr4");
        webTestClient.post().uri("stock/create").body(Mono.just(stockDTO), StockDTO.class).exchange().expectStatus().isCreated().expectBody(StockDTO.class);
    }
}
