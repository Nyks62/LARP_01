document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('conventForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Zapobiega domyślnej akcji formularza
        console.log('potato: start');

        // Pobierz dane z formularza
        const name = document.getElementById('name').value;
        let date = document.getElementById('date').value;

        // Konwersja daty z formatu "DD-MM-YYYY" na "YYYY-MM-DD"
        const [day, month, year] = date.split('-');
        date = `${year}-${month}-${day}`;

        console.log('Name:', name);
        console.log('Date:', date);

        // Utwórz obiekt JSON
        const jsonData = { name: name, date: date };

        console.log('potato json:', jsonData);


        // Przejdź do sekcji, gdzie wysyłane jest żądanie POST
        fetch('/convent/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonData)
        })
            .then(response => {
                if (!response.ok) {
                    // Jeśli odpowiedź nie jest OK, sprawdź, czy zawiera treść
                    return response.text().then(text => {
                        throw new Error(`Wystąpił błąd podczas dodawania konwentu. Status: ${response.status}, Treść: ${text}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                alert('Konwent został dodany pomyślnie!');
                // Przejdź do strony głównej po dodaniu konwentu
                window.location.href = '/convent/main';
            })
            .catch(error => {
                console.error('Błąd podczas wysyłania żądania:', error);
                console.log('Odpowiedź z serwera:', error.response); // Dodaj to, aby zobaczyć odpowiedź z serwera
                alert('Wystąpił błąd podczas dodawania konwentu. Sprawdź konsolę aby uzyskać więcej informacji.');
            });
    });
});



