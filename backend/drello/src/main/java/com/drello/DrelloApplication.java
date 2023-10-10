package com.drello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrelloApplication.class, args);
	}
}
