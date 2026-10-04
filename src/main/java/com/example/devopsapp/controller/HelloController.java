package com.example.devopsapp.controller;

import com.example.devopsapp.service.GreetingService;
import java.net.InetAddress;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    @Value("${app.version:1.0.0}")
    private String version;

    @Value("${app.env:development}")
    private String environment;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("message", "Spring Boot app deployed through a CI/CD pipeline");
        body.put("version", version);
        return body;
    }

    @GetMapping("/api/greet")
    public Map<String, String> greet(@RequestParam(defaultValue = "World") String name) {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("greeting", greetingService.greet(name));
        return body;
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("app", "devops-app");
        body.put("version", version);
        body.put("environment", environment);
        body.put("hostname", hostname());
        body.put("timeUtc", Instant.now().toString());
        return body;
    }

    private String hostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown";
        }
    }
}
