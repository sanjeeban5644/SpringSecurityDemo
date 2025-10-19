  const loginTab = document.getElementById("loginTab");
  const signupTab = document.getElementById("signupTab");
  const loginForm = document.getElementById("loginForm");
  const signupForm = document.getElementById("signupForm");

  loginTab.addEventListener("click", () => {
    loginForm.classList.remove("hidden");
    signupForm.classList.add("hidden");
    loginTab.classList.add("active");
    signupTab.classList.remove("active");
  });

  signupTab.addEventListener("click", () => {
    signupForm.classList.remove("hidden");
    loginForm.classList.add("hidden");
    signupTab.classList.add("active");
    loginTab.classList.remove("active");
  });

  // Sign-up logic
  document.getElementById("signupForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("signupUsername").value.trim();
    const password = document.getElementById("signupPassword").value.trim();
    const msg = document.getElementById("signupMsg");
    msg.textContent = "";

    try {
      const response = await fetch("/signUp", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });

      if (response.ok) {
        msg.textContent = "✅ Sign-up successful! Please log in.";
        msg.className = "success";
      } else {
        msg.textContent = "⚠️ Sign-up failed. Username may already exist.";
        msg.className = "error";
      }
    } catch (err) {
      msg.textContent = "Server error during sign-up.";
      msg.className = "error";
    }
  });

  // Login logic
  document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("loginUsername").value.trim();
    const password = document.getElementById("loginPassword").value.trim();
    const msg = document.getElementById("loginMsg");
    msg.textContent = "";

    try {
      const response = await fetch("/loginPage", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });

      if (response.ok) {
        const token = await response.text();
        document.cookie = `token=${token}; path=/;`;
        msg.textContent = "✅ Login successful! Redirecting...";
        msg.className = "success";
        setTimeout(() => window.location.href = "admin.html", 1000);
      } else {
        msg.textContent = "❌ Invalid username or password.";
        msg.className = "error";
      }
    } catch (err) {
      msg.textContent = "Server error during login.";
      msg.className = "error";
    }
  });