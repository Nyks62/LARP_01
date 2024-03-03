document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("adminLoginForm");

    loginForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Zapobiega domyślnej akcji formularza (przeładowaniu strony)

    });
});