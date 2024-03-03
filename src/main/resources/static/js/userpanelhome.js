document.addEventListener("DOMContentLoaded", function() {
    // Dodajemy nasłuchiwanie zdarzeń dla przycisków
    const loginButton = document.querySelector('a[href="/userlogin"]');
    const registerButton = document.querySelector('a[href="/userregister"]');

    loginButton.addEventListener("click", function(e) {
        e.preventDefault(); // Zapobiegamy domyślnemu zachowaniu odnośnika
        window.location.href = "/userlogin"; // Przekierowanie do strony logowania
    });

    registerButton.addEventListener("click", function(e) {
        e.preventDefault(); // Zapobiegamy domyślnemu zachowaniu odnośnika
        window.location.href = "/userregister"; // Przekierowanie do strony rejestracji
    });
});