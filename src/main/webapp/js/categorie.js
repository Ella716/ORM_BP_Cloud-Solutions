document.addEventListener('DOMContentLoaded', function() {
    // Load categories from the server
    loadCategories();

    // Handle form submission
    document.getElementById('categorie-form').addEventListener('submit', function(event) {
        event.preventDefault();
        saveCategorie();
    });

    // Handle edit button click
    document.getElementById('categorie-table-body').addEventListener('click', function(event) {
        if (event.target.classList.contains('edit-btn')) {
            editCategorie(event.target.dataset.id);
        }
    });

    // Handle delete button click
    document.getElementById('categorie-table-body').addEventListener('click', function(event) {
        if (event.target.classList.contains('delete-btn')) {
            deleteCategorie(event.target.dataset.id);
        }
    });
});

// Load categories from the server
function loadCategories() {
    fetch('http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/categorieen')
        .then(response => response.json())
        .then(categories => {
            let tableBody = document.getElementById('categorie-table-body');
            tableBody.innerHTML = '';
            categories.forEach(category => {
                let row = document.createElement('tr');
                row.innerHTML = `
                    <td>${category.categorieId}</td>
                    <td>${category.naam}</td>
                    <td>
                        <button type="button" class="btn btn-primary edit-btn" data-id="${category.categorieId}">Edit</button>
                        <button type="button" class="btn btn-danger delete-btn" data-id="${category.categorieId}">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        });
}

// Save a categorie to the server
function saveCategorie() {
    let id = document.getElementById('categorie-id').value;
    let name = document.getElementById('categorie-name').value;

    if (id === '') {
        // Create a new categorie
        fetch('/api/categorieen', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ naam: name })
        })
            .then(response => response.json())
            .then(category => {
                loadCategories();
                $('#categorie-modal').modal('hide');
            });
    } else {
        // Update an existing categorie
        fetch('/api/categorieen/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ naam: name })
        })
            .then(response => response.json())
            .then(category => {
                loadCategories();
                $('#categorie-modal').modal('hide');
            });
    }
}

// Edit a categorie
function editCategorie(id) {
    fetch('/api/categorieen/' + id)
        .then(response => response.json())
        .then(category => {
            document.getElementById('categorie-id').value = category.categorieId;
            document.getElementById('categorie-name').value = category.naam;
            $('#categorie-modal').modal('show');
        });
}

// Delete a categorie
function deleteCategorie(id) {
    if (confirm('Are you sure you want to delete this categorie?')) {
        fetch('/api/categorieen/' + id, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(() => {
                loadCategories();
            });
    }
}