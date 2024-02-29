// Skrypt JavaScript dla panelu logowania użytkownika

document.getElementById('userLoginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Zapobieganie domyślnej akcji przesyłania formularza

    // Pobieranie danych z formularza
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    // Walidacja danych
    if (!email || !password) {
        document.getElementById('error').innerText = 'Wypełnij wszystkie pola';
        return;
    }

    // Wysyłanie formularza
    this.submit();
});