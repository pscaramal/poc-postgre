package com.example.pocpostgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
public class PocPostgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocPostgreApplication.class, args);
	}

}
