const urlParams = new URLSearchParams(window.location.search);
if (urlParams.has('error')) {
    const errorMessage = document.getElementById('error-message');
    errorMessage.textContent = 'Bad credentials';
    errorMessage.style.display = 'block';
}