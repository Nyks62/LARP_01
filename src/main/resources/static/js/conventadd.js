document.addEventListener('DOMContentLoaded', function() {
    const conventForm = document.getElementById('conventForm');
    const tagSelect = document.getElementById('tag');

    // Funkcja do pobrania tagów i wygenerowania opcji w select
    function loadTags() {
        const predefinedTags = ['postapo', 'fantasy', 'battlelarp', 'high-larpówek', 'cyberpunk', 'reko', 'steampunk', 'chamber', 'gra miejska']; // Lista predefiniowanych tagów

        // Wyczyść istniejące opcje przed dodaniem nowych
        tagSelect.innerHTML = '';

        // Iteruj przez listę predefiniowanych tagów i dodaj każdy tag jako opcję w select
        predefinedTags.forEach(tag => {
            const option = document.createElement('option');
            option.value = tag;
            option.textContent = tag;
            tagSelect.appendChild(option);
        });
    }

    // Wywołaj funkcję, aby załadować tagi przy ładowaniu strony
    loadTags();

    conventForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const date = document.getElementById('date').value;
        const tag = document.getElementById('tag').value;

        const [day, month, year] = date.split('-');
        const formattedDate = `${year}-${month}-${day}`;

        const jsonData = { name: name, date: formattedDate, tag: tag };

        fetch('/convent/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(jsonData)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Wystąpił błąd podczas dodawania konwentu.');
            })
            .then(data => {
                console.log('Odpowiedź z serwera:', data);
                alert('Konwent został dodany pomyślnie!');
                window.location.href = '/convent/main';
            })
            .catch(error => {
                console.error('Błąd podczas wysyłania żądania:', error);
                alert('Wystąpił błąd podczas dodawania konwentu. Sprawdź konsolę aby uzyskać więcej informacji.');
            });
    });
});
