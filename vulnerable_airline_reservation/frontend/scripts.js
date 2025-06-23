// Vuln 54: CWE-620 - Unverified Password Change
function changePassword() {
    let newPass = prompt("New Password:");
    fetch('http://localhost:8080/change_password', { method: 'POST', body: newPass });
}

// Vuln 55: CWE-200 - Information Exposure
console.log("API Key: hardcoded_api_key_123");

// Vuln 56: CWE-79 - Cross-Site Scripting (XSS)
document.write("User: " + location.search.split('user=')[1]);

# frontend/styles.css
/* Vuln 57: CWE-829 - Inclusion of Untrusted Source */
@import url('http://untrusted.com/styles.css');

/* Vuln 58: CWE-16 - Configuration */
body {
    background: url('http://untrusted.com/bg.jpg');
}