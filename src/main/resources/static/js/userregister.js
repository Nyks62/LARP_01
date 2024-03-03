document.addEventListener("DOMContentLoaded", function() {
    const registrationForm = document.getElementById("registrationForm");
    const errorMessage = document.getElementById("errorMessage");

    registrationForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Zapobiegamy domyślnej akcji formularza

        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        // Sprawdzamy, czy hasła się zgadzają
        if (password !== confirmPassword) {
            errorMessage.textContent = "Hasła nie są identyczne";
            return;
        }

        // Tutaj możesz dodać dodatkową walidację formularza w JavaScript, jeśli jest potrzebna

        // Jeśli wszystko jest w porządku, możemy zatwierdzić formularz
        registrationForm.submit();
    });
});