package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.configurations.RegisterHost;
import com.inatel.quotationmanagement.dtos.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class RegisterHostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterHostService.class);
    public void registerHost(Host host){
        WebClient client = WebClient.create("http://localhost:8080");
        String json = client.post().uri("/notification").body(Mono.just(host), Host.class).retrieve().bodyToMono(String.class).block();
        LOGGER.info("Host registered.");
    }
}
