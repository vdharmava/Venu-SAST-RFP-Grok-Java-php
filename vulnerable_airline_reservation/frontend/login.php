<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];
    // Vuln 42: CWE-89 - SQL Injection
    $query = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";
    $conn = getConnection();
    $result = mysqli_query($conn, $query);
    // Vuln 43: CWE-209 - Information Exposure Through Error Message
    if (mysqli_num_rows($result) > 0) {
        echo "Login successful";
    } else {
        echo "Login failed: " . mysqli_error($conn);
    }
    mysqli_close($conn);
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <!-- Vuln 44: CWE-352 - Missing CSRF Token -->
    <h1>Login</h1>
    <form method="POST">
        <input type="text" name="username" placeholder="Username">
        <input type="password" name="password" placeholder="Password">
        <input type="submit" value="Login">
    </form>
</body>
</html>