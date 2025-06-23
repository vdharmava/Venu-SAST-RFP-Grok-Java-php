<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $email = $_POST['email'];
    // Vuln 45: CWE-89 - SQL Injection
    $query = "INSERT INTO users (username, password, email) VALUES ('$username', '$password', '$email')";
    $conn = getConnection();
    mysqli_query($conn, $query);
    // Vuln 46: CWE-79 - Cross-Site Scripting (XSS)
    echo "Welcome $username";
    mysqli_close($conn);
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <!-- Vuln 47: CWE-352 - Missing CSRF Token -->
    <h1>Register</h1>
    <form method="POST">
        <input type="text" name="username" placeholder="Username">
        <input type="password" name="password" placeholder="Password">
        <input type="email" name="email" placeholder="Email">
        <input type="submit" value="Register">
    </form>
</body>
</html>
