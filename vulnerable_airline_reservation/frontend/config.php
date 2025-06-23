<?php
// Vuln 36: CWE-257 - Storing Passwords in Plaintext
define('DB_HOST', 'localhost');
define('DB_USER', 'admin');
define('DB_PASS', 'admin123');
define('DB_NAME', 'airline');

// Vuln 37: CWE-321 - Hardcoded Cryptographic Key
define('SECRET_KEY', 'hardcoded_secret_123');

function getConnection() {
    // Vuln 38: CWE-319 - Cleartext Transmission of Sensitive Information
    $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME);
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error()); // Vuln 39: CWE-209
    }
    return $conn;
}
?>