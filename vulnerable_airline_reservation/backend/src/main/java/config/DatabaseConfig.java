package com.vulnerable.airline.config;

import java.sql.Connection;
import java.sql.DriverManager;

// Vuln 4: CWE-259 - Hardcoded Password
public class DatabaseConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airline";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "admin123"; // Vuln 5: CWE-257

    public static Connection getConnection() throws Exception {
        // Vuln 6: CWE-321 - Hardcoded Cryptographic Key
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
