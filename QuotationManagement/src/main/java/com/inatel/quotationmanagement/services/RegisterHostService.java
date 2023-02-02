package com.inatel.quotationmanagement.services;

import com.inatel.quotationmanagement.dtos.Host;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class RegisterHostService {


    public void registerHost(Host host){
        WebClient client = WebClient.create("http://localhost:8080");
        Mono<String> json = client.get().uri("/notification").retrieve().bodyToMono(String.class);
    }
}
