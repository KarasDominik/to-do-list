document.getElementById("register-form").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch('/api/v1/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(res => res.json())
        .then(data => {
            let message = document.getElementById("message");
            if (!message) {
                message = document.createElement("p");
                message.id = "message";
            }
            if (data.userId) {
                message.textContent = "User created successfully!";
                message.style.color = "green";
            } else {
                message.textContent = "Failed to create user: " + (data.errorMessage || "Unknown error");
                message.style.color = "red";
            }
            message.style.textAlign = "center";
            document.body.appendChild(message);
        })
        .finally(() => {
            document.getElementById("register-form").reset();
        })
})