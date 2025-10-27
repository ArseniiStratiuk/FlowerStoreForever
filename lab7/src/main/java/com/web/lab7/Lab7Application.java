package com.web.lab7;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point for the Flower Store Spring Boot application and a tiny demo API.
 */
@SpringBootApplication
@RestController
public final class Lab7Application {

    /**
     * Canned responses used by the demo endpoint.
     */
    private static final List<String> DEMO_MESSAGES = List.of(
        "Welcome to the Flower Store API",
        "This is a basic @RestController example"
    );

    private Lab7Application() {
    }

    /**
     * Bootstraps the Spring application.
     *
     * @param args command-line arguments supplied by the runtime
     */
    public static void main(final String[] args) {
        SpringApplication.run(Lab7Application.class, args);
    }

    /**
     * Exposes a static list of demo messages under {@code /api/demo/messages}.
     *
     * @return simple informational messages
     */
    @GetMapping("/api/demo/messages")
    public List<String> demoMessages() {
        return DEMO_MESSAGES;
    }
}
