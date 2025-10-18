document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const errorMsg = document.getElementById("errorMsg");
    errorMsg.textContent = "";

    try {
        // Call your Spring Boot backend login endpoint (adjust URL if needed)
        const response = await fetch("/signUp", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            const data = await response.json();

            // Example: backend sends { "role": "ADMIN" } or { "role": "TEACHER" }
            if (data.role === "ADMIN") {
                window.location.href = "admin.html";
            } else if (data.role === "TEACHER") {
                window.location.href = "teacher.html";
            } else {
                errorMsg.textContent = "Unknown role.";
            }
        } else {
            errorMsg.textContent = "Invalid username or password.";
        }
    } catch (error) {
        console.error("Error:", error);
        errorMsg.textContent = "Server error.";
    }
});
