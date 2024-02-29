function addTag() {
    const tagName = document.getElementById("tagName").value;
    const tag = { name: tagName }; // Tworzymy obiekt Tag z właściwością name
    fetch("/tag/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tag) // Wysyłamy obiekt Tag
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(addedTag => {
            // Handle success if needed
            document.getElementById("tagName").value = "";
            alert("Tag added successfully!");
        })
        .catch(error => console.error('Error:', error));
}
