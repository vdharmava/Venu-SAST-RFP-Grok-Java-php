<?php
require_once 'config.php';

if (isset($_GET['flight_number'])) {
    // Vuln 48: CWE-89 - SQL Injection
    $flightNumber = $_GET['flight_number'];
    $query = "SELECT * FROM flights WHERE flight_number = '$flightNumber'";
    $conn = getConnection();
    $result = mysqli_query($conn, $query);
    // Vuln 49: CWE-200 - Information Exposure
    while ($row = mysqli_fetch_assoc($result)) {
        echo $row['flight_number'] . ": " . $row['destination'] . "<br>";
    }
    mysqli_close($conn);
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Flights</title>
</head>
<body>
    <!-- Vuln 50: CWE-79 - Cross-Site Scripting (XSS) -->
    <h1>Search Flights</h1>
    <form method="GET">
        <input type="text" name="flight_number" placeholder="Flight Number">
        <input type="submit" value="Search">
    </form>
    <script>
        document.write("Search: " + location.search.split('flight_number=')[1]);
    </script>
</body>
</html>