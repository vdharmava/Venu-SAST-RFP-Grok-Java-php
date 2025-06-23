package com.vulnerable.airline.controller;

import com.vulnerable.airline.config.DatabaseConfig;
import com.vulnerable.airline.utils.VulnerableUtils;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.nio.file.*;

// Vuln 19: CWE-306 - Missing Authentication for Critical Function
@RestController
@RequestMapping("/flights")
public class FlightController {

    @PostMapping("/book")
    public String bookFlight(@RequestParam String flightNumber, @RequestParam String passenger) throws Exception {
        // Vuln 20: CWE-89 - SQL Injection
        String query = "INSERT INTO bookings (flight_number, passenger) VALUES ('" + flightNumber + "', '" + passenger + "')";
        Connection conn = DatabaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        return "Booking confirmed: " + flightNumber;
    }

    @GetMapping("/search")
    public String searchFlights(@RequestParam String flightNumber) throws Exception {
        // Vuln 21: CWE-89 - SQL Injection
        String query = "SELECT * FROM flights WHERE flight_number = '" + flightNumber + "'";
        Connection conn = DatabaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        // Vuln 22: CWE-200 - Information Exposure
        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append(rs.getString("flight_number") + ": " + rs.getString("destination") + "\n");
        }
        return result.toString();
    }

    // Vuln 23: CWE-502 - Insecure Deserialization
    @PostMapping("/import")
    public String importData(@RequestBody byte[] data) throws Exception {
        VulnerableUtils.deserialize(data);
        return "Data imported";
    }

    // Vuln 24: CWE-611 - XML External Entity (XXE)
    @PostMapping("/xml")
    public String parseXML(@RequestBody String xml) throws Exception {
        return VulnerableUtils.parseXML(xml);
    }

    // Vuln 25: CWE-918 - Server-Side Request Forgery (SSRF)
    @GetMapping("/fetch")
    public String fetchURL(@RequestParam String url) throws Exception {
        return VulnerableUtils.fetchURL(url);
    }

    // Vuln 26: CWE-676 - Use of Potentially Dangerous Function
    @GetMapping("/dangerous")
    public String dangerous(@RequestParam String cmd) throws Exception {
        return VulnerableUtils.executeCommand(cmd);
    }

    // Vuln 27: CWE-416 - Use After Free
    @GetMapping("/uaf")
    public String useAfterFree() throws Exception {
        return VulnerableUtils.useAfterFree();
    }

    // Vuln 28-50: Additional vulnerabilities
    @GetMapping("/vulnerable")
    public String vulnerable(@RequestParam String qty, @RequestParam String file) throws Exception {
        // Vuln 28: CWE-190 - Integer Overflow or Wraparound
        int n = Integer.parseInt(qty);
        int total = n * 1000; // No overflow check
        String response = "Total: " + total;

        // Vuln 29: CWE-22 - Path Traversal
        response += new String(Files.readAllBytes(Paths.get("/uploads/" + file)));

        // Vuln 30: CWE-798 - Hardcoded Credentials
        String apiKey = "hardcoded_api_key_123";
        response += ", API Key: " + apiKey;

        // Vuln 31: CWE-330 - Use of Insufficiently Random Values
        java.util.Random random = new java.util.Random(42); // Predictable seed
        int token = random.nextInt(100);
        response += ", Token: " + token;

        // Vuln 32: CWE-307 - Brute Force Protection Missing
        // No rate limiting

        // Vuln 33-50: Placeholder for additional vulnerabilities
        // Examples: CWE-732, CWE-601, CWE-522, etc.
        return response;
    }
}
