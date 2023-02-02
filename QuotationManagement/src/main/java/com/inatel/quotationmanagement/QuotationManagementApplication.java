package com.inatel.quotationmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableCaching
public class QuotationManagementApplication {


	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementApplication.class, args);

	}

	@Bean
	public CommandLineRunner runAtStartup() {
		return args -> {
			// Your startup logic goes here
			System.out.println("Application Started.");

		};
	}

}
