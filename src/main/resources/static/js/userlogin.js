document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    const errorMessage = document.getElementById("errorMessage");

    loginForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Zapobiegamy domyślnej akcji formularza

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // Tutaj możesz dodać dodatkową walidację danych logowania w JavaScript, jeśli jest potrzebna

        // Jeśli wszystko jest w porządku, możemy zatwierdzić formularz
        loginForm.submit();
    });
});