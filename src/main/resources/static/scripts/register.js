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
            let message = createMessageIfNotExists();
            if (userCreated(data)) {
                handleSuccess(message);
            } else {
                handleError(data, message);
            }
            displayMessage(message);
        })
});

function createMessageIfNotExists() {
    let message = document.getElementById("message");
    if (!message) {
        message = document.createElement("p");
        message.id = "message";
    }
    return message;
}

function clearForm() {
    document.getElementById("register-form").reset();
}

function userCreated(data) {
    return data.userId;
}

function handleSuccess(message) {
    createMessageContent(message, "User created successfully!", "green")
    clearForm();
}

function createMessageContent(message, content, color) {
    message.textContent = content;
    message.style.color = color;
}

function handleError(data, message) {
    const errorMessage = "Failed to create user: " + (data.errorMessage || "Unknown error");
    createMessageContent(message, errorMessage, "red")
}

function displayMessage(message) {
    message.style.textAlign = "center";
    document.body.appendChild(message);
}