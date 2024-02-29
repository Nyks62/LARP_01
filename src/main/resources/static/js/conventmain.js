document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('conventForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Zapobiega domyślnej akcji formularza

        // Pobierz dane z formularza
        const name = document.getElementById('name').value;
        const date = document.getElementById('date').value;

        // Utwórz obiekt FormData i dodaj do niego dane formularza
        const formData = new FormData();
        formData.append('name', name);
        formData.append('date', date);

        // Wyślij zapytanie POST do serwera
        fetch('/convent/add', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Wystąpił błąd podczas dodawania konwentu.');
                }
                return response.json();
            })
            .then(data => {
                alert(data.message); // Wyświetl komunikat zwrotny
                // Przeładuj stronę po dodaniu konwentu
                window.location.reload();
            })
            .catch(error => {
                alert(error.message);
            });
    });
});
