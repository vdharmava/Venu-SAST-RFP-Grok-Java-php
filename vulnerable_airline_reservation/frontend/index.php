<?php
// Vuln 40: CWE-79 - Cross-Site Scripting (XSS)
$user = $_GET['user'] ?? '';
echo "Welcome $user";
?>
<!DOCTYPE html>
<html>
<head>
    <title>Airline Reservation</title>
    <link rel="stylesheet" href="styles.css">
    <script src="scripts.js"></script>
</head>
<body>
    <h1>Airline Reservation System</h1>
    <a href="login.php">Login</a> | <a href="register.php">Register</a> | <a href="flights.php">Flights</a> | <a href="book.php">Book</a>
    <!-- Vuln 41: CWE-79 - Cross-Site Scripting (XSS) -->
    <script>
        document.write("User: " + location.search.split('user=')[1]);
    </script>
</body>
</html>
