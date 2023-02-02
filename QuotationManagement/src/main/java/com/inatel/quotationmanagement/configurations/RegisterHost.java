package com.inatel.quotationmanagement.configurations;

import com.inatel.quotationmanagement.dtos.Host;
import com.inatel.quotationmanagement.services.RegisterHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RegisterHost implements CommandLineRunner {

    @Autowired
    private RegisterHostService registerHostService;

    @Override
    public void run(String... args) throws Exception {
        registerHostService.registerHost(new Host("localhost", 8081));
    }
}
