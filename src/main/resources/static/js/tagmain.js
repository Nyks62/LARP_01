document.addEventListener("DOMContentLoaded", function() {
    loadTag();
});

function loadTag() {
    fetch("/tag/list")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(tag => {
            const tagListDiv = document.getElementById("tagList");
            if (tag.length === 0) {
                tagListDiv.textContent = "No tags available.";
            } else {
                tagListDiv.innerHTML = "<ul>" + tag.map(tag => `<li>${tag.name}</li>`).join("") + "</ul>";
            }
        })
        .catch(error => console.error('Error:', error));
}
