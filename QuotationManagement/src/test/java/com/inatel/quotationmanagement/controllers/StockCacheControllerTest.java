package com.inatel.quotationmanagement.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("StockCacheController funcionality tests.")
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Order(3)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockCacheControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DisplayName("Return no content status on request.")
    public void shouldReturnNoContentStatus(){
        webTestClient.delete().uri("/stockcache").exchange().expectStatus().isNoContent();
    }
}
