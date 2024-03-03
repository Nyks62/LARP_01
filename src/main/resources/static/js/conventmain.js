document.addEventListener('DOMContentLoaded', function() {
    // Pobierz miejsce w HTML, gdzie ma być wyświetlona lista konwentów
    const conventList = document.getElementById('conventList');

    // Funkcja do pobrania i wyświetlenia listy konwentów
    function displayConvents() {
        // Wyślij zapytanie GET do serwera o pobranie listy konwentów
        fetch('/convent/all')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Wystąpił błąd podczas pobierania listy konwentów.');
                }
                return response.json();
            })
            .then(data => {
                // Wyczyść listę konwentów przed wyświetleniem nowych danych
                conventList.innerHTML = '';

                // Iteruj przez otrzymane dane i dodaj każdy konwent do listy
                data.forEach(convent => {
                    const listItem = document.createElement('li');
                    listItem.textContent = `${convent.name} - ${convent.date}`;

                    // Dodaj tagi konwentu do listy
                    if (convent.tags && convent.tags.length > 0) {
                        const tagList = document.createElement('ul');
                        convent.tags.forEach(tag => {
                            const tagItem = document.createElement('li');
                            tagItem.textContent = tag.name;
                            tagList.appendChild(tagItem);
                        });
                        listItem.appendChild(tagList);
                    }

                    conventList.appendChild(listItem);
                });
            })
            .catch(error => {
                console.error(error);
                // Obsłuż błąd, jeśli wystąpił
            });
    }

    // Wywołaj funkcję, aby wyświetlić listę konwentów po załadowaniu strony
    displayConvents();
});