package com.web.lab7;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Lab7Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@GetMapping("/api/demo/messages")
	public List<String> demoMessages() {
		return List.of("Welcome to the Flower Store API", "This is a basic @RestController example");
	}

}
