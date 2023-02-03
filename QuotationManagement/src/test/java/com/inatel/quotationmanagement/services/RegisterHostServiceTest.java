package com.inatel.quotationmanagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.quotationmanagement.dtos.Host;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DisplayName("RegisterHostService funcionality tests.")
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@Order(4)
public class RegisterHostServiceTest {





    @Autowired
    private RegisterHostService registerHostService;


    @Test
    @DisplayName("Register host on call")
    public void registerHostOnCall() throws JsonProcessingException {
        WebClient client = WebClient.create("http://localhost:8080");
        String json = client.get().uri("/notification").retrieve().bodyToMono(String.class).block();
        registerHostService.registerHost(new Host("localhost", 8082));
        String newJson = client.get().uri("/notification").retrieve().bodyToMono(String.class).block();
        assertThat(json.toCharArray().length).isLessThan(newJson.toCharArray().length);
    }

}
