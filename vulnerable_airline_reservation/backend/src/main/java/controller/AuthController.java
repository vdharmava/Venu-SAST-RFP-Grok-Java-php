package com.vulnerable.airline.controller;

import com.vulnerable.airline.config.DatabaseConfig;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

// Vuln 13: CWE-352 - Missing CSRF Protection
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        // Vuln 14: CWE-89 - SQL Injection
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        Connection conn = DatabaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        // Vuln 15: CWE-209 - Information Exposure Through Error Message
        if (rs.next()) {
            return "Login successful";
        } else {
            return "Login failed: " + conn.getWarnings();
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) throws Exception {
        // Vuln 16: CWE-89 - SQL Injection
        String query = "INSERT INTO users (username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
        Connection conn = DatabaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        // Vuln 17: CWE-79 - Cross-Site Scripting (XSS)
        return "Welcome " + username;
    }

    // Vuln 18: CWE-269 - Improper Privilege Management
    @GetMapping("/make_admin")
    public String makeAdmin(@RequestParam String username) throws Exception {
        String query = "UPDATE users SET role = 'admin' WHERE username = '" + username + "'";
        Connection conn = DatabaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        return "User promoted";
    }
}