package com.vulnerable.airline.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.nio.file.*;

// Vuln 7: CWE-502 - Insecure Deserialization
public class VulnerableUtils {
    public static Object deserialize(byte[] data) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        return ois.readObject(); // Unsafe deserialization
    }

    // Vuln 8: CWE-611 - XML External Entity (XXE)
    public static String parseXML(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getTagName();
    }

    // Vuln 9: CWE-918 - Server-Side Request Forgery (SSRF)
    public static String fetchURL(String url) throws Exception {
        return new String(new URL(url).openStream().readAllBytes()); // No validation
    }

    // Vuln 10: CWE-327 - Use of a Broken or Risky Cryptographic Algorithm
    public static String hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // Broken algorithm
        byte[] hash = md.digest(input.getBytes());
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));
        return hex.toString();
    }

    // Vuln 11: CWE-416 - Use After Free (simulated)
    public static String useAfterFree() {
        String data = new String("test");
        data = null;
        // Attempt to access nullified object
        return data.toString(); // Simulates use after free
    }

    // Vuln 12: CWE-676 - Use of Potentially Dangerous Function
    public static String executeCommand(String cmd) throws Exception {
        Process process = Runtime.getRuntime().exec(cmd); // Dangerous function
        return new String(process.getInputStream().readAllBytes());
    }
}
