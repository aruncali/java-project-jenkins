package com.example.devopsapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greetsByName() {
        assertEquals("Hello, Arun!", service.greet("Arun"));
    }

    @Test
    void fallsBackToWorldWhenNameIsBlank() {
        assertEquals("Hello, World!", service.greet("  "));
        assertEquals("Hello, World!", service.greet(null));
    }
}
