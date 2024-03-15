// URL van de REST-service voor het ophalen van categoriegegevens
const categorieenUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/categorieen';

// Functie om categoriegegevens op te halen via een HTTP GET-verzoek
function haalCategorieenOp() {
    fetch(categorieenUrl)
        .then(response => response.json())
        .then(data => {
            // Roep de functie aan om de categoriegegevens in de tabel weer te geven
            toonCategorieen(data);
        })
        .catch(error => console.error('Er is een fout opgetreden bij het ophalen van categoriegegevens:', error));
}

// Functie om categoriegegevens in de tabel weer te geven
function toonCategorieen(categorieen) {
    const categorieentabel = document.getElementById('categorieen-list');

    // Wis alle bestaande rijen in de tabel
    categorieentabel.innerHTML = '';

    // Voeg elke categorie toe aan de tabel als een rij
    categorieen.forEach(categorie => {
        const rij = categorieentabel.insertRow();

        // Voeg cellen toe voor elke eigenschap van de categorie
        const idCel = rij.insertCell();
        idCel.textContent = categorie.categorieId;

        const naamCel = rij.insertCell();
        naamCel.textContent = categorie.naam;

        // Voeg bewerkings- en verwijderingsknoppen toe met respectievelijke functies
        const actieCel = rij.insertCell();
        const bewerkKnop = document.createElement('button');
        bewerkKnop.textContent = 'Bewerken';
        bewerkKnop.classList.add('btn', 'btn-primary', 'me-2'); // Voeg Bootstrap-klassen toe
        bewerkKnop.addEventListener('click', () => bewerkCategorie(categorie));
        actieCel.appendChild(bewerkKnop);

        const verwijderKnop = document.createElement('button');
        verwijderKnop.textContent = 'Verwijderen';
        verwijderKnop.classList.add('btn', 'btn-danger'); // Voeg Bootstrap-klassen toe
        verwijderKnop.addEventListener('click', () => verwijderCategorie(categorie.categorieId));
        actieCel.appendChild(verwijderKnop);

    });
}

class CategorieDTO {
    constructor(categorieId, naam) {
        this.categorieId = categorieId;
        this.naam = naam;
    }
}


// Functie om een categorie te bewerken
function bewerkCategorie(categorie) {
    // Modal venster openen (using Bootstrap's JavaScript)
    const bewerkModal = new bootstrap.Modal(document.getElementById("bewerkCategorieModal"));
    bewerkModal.show();

    // Invullen van de invoervelden met categoriegegevens
    document.getElementById('categorieNaam').value = categorie.naam;

    // Event listener toevoegen aan de knop "Opslaan"
    document.getElementById('opslaanCategorieKnop').addEventListener('click', function() {
        // Categoriegegevens ophalen uit de invoervelden
        var bewerkteCategorie = new CategorieDTO(categorie.categorieId, document.getElementById('categorieNaam').value);

        // Verstuur de bewerkte categorie naar de backend om op te slaan
        bewerkCategorieInBackend(bewerkteCategorie);
    });
}

// Functie om een bewerkte categorie naar de backend te sturen
function bewerkCategorieInBackend(bewerkteCategorie) {
    // Endpoint voor het bewerken van een categorie in de backend
    var endpoint = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/categorieen/' + bewerkteCategorie.categorieId;

    // Opties voor het HTTP-verzoek
    var options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bewerkteCategorie)
    };

    // HTTP-verzoek uitvoeren met behulp van Fetch API
    fetch(endpoint, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Er is een fout opgetreden bij het bijwerken van de categorie.');
            }
            return response.json();
        })
        .then(data => {
            // Verwerken van de respons van de backend, bijvoorbeeld notificatie weergeven
            console.log('Categorie succesvol bijgewerkt:', data);
            // Voeg hier code toe om een succesmelding te tonen aan de gebruiker

            // Sluit de bewerkingsmodal
            const modal = new bootstrap.Modal(document.getElementById('bewerkCategorieModal'));
            modal.hide();
        })
        .catch(error => {
            console.error('Fout bij het bijwerken van de categorie:', error.message);
            // Voeg hier code toe om een foutmelding te tonen aan de gebruiker
        });
}

// Functie om een categorie te verwijderen
function verwijderCategorie(categorieId) {
    // Bevestiging vragen aan de gebruiker voordat een categorie wordt verwijderd
    var bevestigen = confirm("Weet je zeker dat je deze categorie wilt verwijderen?");

    if (bevestigen) {
        // URL voor het verwijderen van een categorie, aangepast met de categorieId
        var verwijderUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/categorieen/' + categorieId;

        // HTTP DELETE-verzoek uitvoeren naar het eindpunt voor het verwijderen van een categorie
        fetch(verwijderUrl, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // Als het verwijderen succesvol is, vernieuw de categorieënlijst
                    haalCategorieenOp();
                } else {
                    // Als er een fout optreedt, toon een melding aan de gebruiker
                    alert('Er is een fout opgetreden bij het verwijderen van de categorie.');
                }
            })
            .catch(error => {
                console.error('Er is een fout opgetreden bij het verwijderen van de categorie:', error);
                alert('Er is een fout opgetreden bij het verwijderen van de categorie.');
            });
    }
}

// Roep de functie aan om categoriegegevens op te halen wanneer de pagina geladen is
document.addEventListener('DOMContentLoaded', haalCategorieenOp);
