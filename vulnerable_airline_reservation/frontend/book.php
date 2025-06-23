<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $flightNumber = $_POST['flight_number'];
    $passenger = $_POST['passenger'];
    // Vuln 51: CWE-89 - SQL Injection
    $query = "INSERT INTO bookings (flight_number, passenger) VALUES ('$flightNumber', '$passenger')";
    $conn = getConnection();
    mysqli_query($conn, $query);
    // Vuln 52: CWE-79 - Cross-Site Scripting (XSS)
    echo "Booking confirmed: $flightNumber";
    mysqli_close($conn);
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Book Flight</title>
</head>
<body>
    <!-- Vuln 53: CWE-352 - Missing CSRF Token -->
    <h1>Book Flight</h1>
    <form method="POST">
        <input type="text" name="flight_number" placeholder="Flight Number">
        <input type="text" name="passenger" placeholder="Passenger Name">
        <input type="submit" value="Book">
    </form>
</body>
</html>