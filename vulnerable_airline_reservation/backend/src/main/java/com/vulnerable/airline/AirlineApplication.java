package com.vulnerable.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Vuln 1: CWE-16 - Configuration
@SpringBootApplication
public class AirlineApplication {
    public static void main(String[] args) {
        // Vuln 2: CWE-200 - Information Exposure
        System.setProperty("spring.devtools.restart.enabled", "true"); // Debug mode
        SpringApplication.run(AirlineApplication.class, args);
        System.out.println("Secret Key: hardcoded_secret_123"); // Vuln 3: CWE-321
    }
}
