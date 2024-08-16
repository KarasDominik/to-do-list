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
            let alert = createAlertIfNotExists();
            if (userCreated(data)) {
                handleSuccess(alert);
            } else {
                handleError(data, alert);
            }
            displayAlert(alert);
        })
});

function createAlertIfNotExists() {
    let alert = document.getElementById("alert-message");
    if (!alert) {
        alert = document.createElement("div");
        alert.id = "alert-message";
        alert.setAttribute("role", "alert");
    }
    return alert;
}

function clearForm() {
    document.getElementById("register-form").reset();
}

function userCreated(data) {
    return data.userId;
}

function handleSuccess(alert) {
    createAlertContent(alert, "User created successfully!", "alert-success")
    clearForm();
}

function createAlertContent(alert, content, alertClass) {
    alert.textContent = content;
    alert.className = `alert ${alertClass}`;
}

function handleError(data, alert) {
    const errorMessage = data.errorMessage || "Failed to create user";
    createAlertContent(alert, errorMessage, "alert-danger")
}

function displayAlert(alert) {
    document.body.appendChild(alert);
}