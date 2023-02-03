package com.inatel.quotationmanagement.controllers;

import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.exceptions.InvalidStockException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@Order(1)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    private Map<Date, Double> quotes;

    private Stock stock;

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
    @DisplayName("Return list on stock/get endpoint")
    @Order(1)
    public void returnListOnGetEndpoint(){
        webTestClient.get().uri("stock/get").exchange().expectStatus().isOk().expectBodyList(Stock.class);
    }

    @Test
    @DisplayName("Return list on stock/get/{id} endpoint")
    @Order(2)
    public void returnListOnGetAllEndpoint(){
        webTestClient.get().uri("/stock/get/petr4").exchange().expectStatus().isOk().expectBodyList(Stock.class).hasSize(1);
    }

    @Test
    @DisplayName("Throw exception when posting unregistered stock")
    @Order(3)
    public void throwExceptionOnUnregisretedStock(){
        stock.setStockId("ABEV3");
        webTestClient.post().uri("stock/create").body(Mono.just(stock), Stock.class).exchange().expectStatus().isBadRequest().expectBody(InvalidStockException.class);
    }

    @Test
    @DisplayName("Should persist registered stock")
    @Order(4)
    public void persistRegisteredStock(){

        webTestClient.post().uri("stock/create").body(Mono.just(stock), Stock.class).exchange().expectStatus().isBadRequest().expectBody(InvalidStockException.class);
    }
}
