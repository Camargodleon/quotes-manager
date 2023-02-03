package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.configurations.RegisterHost;
import com.inatel.quotationmanagement.dtos.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class RegisterHostService {

    @Value("${stockmanager.address}")
    private String address;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterHostService.class);
    public void registerHost(Host host){
        WebClient client = WebClient.create("http://"+address+":8080");
        String json = client.post().uri("/notification").body(Mono.just(host), Host.class).retrieve().bodyToMono(String.class).block();
        LOGGER.info("Host registered.");
    }
}
