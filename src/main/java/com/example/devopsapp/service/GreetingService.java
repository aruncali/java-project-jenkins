package com.example.devopsapp.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            name = "World";
        }
        return "Hello, " + name.trim() + "!";
    }
}
