// URL van de REST-service voor het ophalen van gebruikersgegevens
const gebruikersUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/gebruikers';

// Functie om gebruikersgegevens op te halen via een HTTP GET-verzoek
function haalGebruikersOp() {
    fetch(gebruikersUrl)
        .then(response => response.json())
        .then(data => {
            // Roep de functie aan om de gebruikersgegevens in de tabel weer te geven
            toonGebruikers(data);
        })
        .catch(error => console.error('Er is een fout opgetreden bij het ophalen van gebruikersgegevens:', error));
}

// Functie om gebruikersgegevens in de tabel weer te geven
function toonGebruikers(gebruikers) {
    const gebruikerstabel = document.getElementById('gebruikers-list');

    // Wis alle bestaande rijen in de tabel
    gebruikerstabel.innerHTML = '';

    // Voeg elke gebruiker toe aan de tabel als een rij
    gebruikers.forEach(gebruiker => {
        const rij = gebruikerstabel.insertRow();

        // Voeg cellen toe voor elke eigenschap van de gebruiker
        const idCel = rij.insertCell();
        idCel.textContent = gebruiker.gebruikerId;

        const naamCel = rij.insertCell();
        naamCel.textContent = gebruiker.naam;

        const emailCel = rij.insertCell();
        emailCel.textContent = gebruiker.email;

        // Voeg bewerkings- en verwijderingsknoppen toe met respectievelijke functies
        const actieCel = rij.insertCell();
        const bewerkKnop = document.createElement('button');
        bewerkKnop.textContent = 'Bewerken';
        bewerkKnop.classList.add('btn', 'btn-primary', 'me-2'); // Voeg Bootstrap-klassen toe
        bewerkKnop.addEventListener('click', () => bewerkGebruiker(gebruiker));
        actieCel.appendChild(bewerkKnop);

        const verwijderKnop = document.createElement('button');
        verwijderKnop.textContent = 'Verwijderen';
        verwijderKnop.classList.add('btn', 'btn-danger'); // Voeg Bootstrap-klassen toe
        verwijderKnop.addEventListener('click', () => verwijderGebruiker(gebruiker.gebruikerId));
        actieCel.appendChild(verwijderKnop);

    });
}

class GebruikerDTO {
    constructor(gebruikerId, naam, email) {
        this.gebruikerId = gebruikerId;
        this.naam = naam;
        this.email = email;
    }
}


// Functie om een gebruiker te bewerken
function bewerkGebruiker(gebruiker) {
    // Modal venster openen (using Bootstrap's JavaScript)
    const bewerkModal = new bootstrap.Modal(document.getElementById("bewerkGebruikerModal"));
    bewerkModal.show();

    // Invullen van de invoervelden met gebruikersgegevens
    document.getElementById('naam').value = gebruiker.naam;
    document.getElementById('email').value = gebruiker.email;

    // Log the 'gebruiker' object to the console
    console.log('Gebruiker:', gebruiker);

    // Event listener toevoegen aan de knop "Opslaan"
    document.getElementById('opslaanKnop').addEventListener('click', function() {
        // Gebruikersgegevens ophalen uit de invoervelden
        var bewerkteGebruiker = new GebruikerDTO(gebruiker.gebruikerId, document.getElementById('naam').value, document.getElementById('email').value);

        // Log the 'bewerkteGebruiker' object to the console
        console.log('Bewerkte gebruiker:', bewerkteGebruiker);

        // Verstuur de bewerkte gebruiker naar de backend om op te slaan
        bewerkGebruikerInBackend(bewerkteGebruiker);
    });

    console.log('Gebruiker bewerken:', gebruiker);
}



// Functie om een bewerkte gebruiker naar de backend te sturen
function bewerkGebruikerInBackend(bewerkteGebruiker) {
    // Log the 'bewerkteGebruiker' object to the console
    console.log('Bewerkte gebruiker:', bewerkteGebruiker);

    // Endpoint voor het bewerken van een gebruiker in de backend
    var endpoint = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/gebruikers/' + bewerkteGebruiker.gebruikerId;

    // Log the endpoint URL to the console
    console.log('Endpoint URL:', endpoint);

    // Opties voor het HTTP-verzoek
    var options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bewerkteGebruiker)
    };

    // HTTP-verzoek uitvoeren met behulp van Fetch API
    fetch(endpoint, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Er is een fout opgetreden bij het bijwerken van de gebruiker.');
            }
            return response.json();
        })
        .then(data => {
            // Verwerken van de respons van de backend, bijvoorbeeld notificatie weergeven
            console.log('Gebruiker succesvol bijgewerkt:', data);
            // Voeg hier code toe om een succesmelding te tonen aan de gebruiker

            // Sluit de bewerkingsmodal
            const bewerkModal = new bootstrap.Modal(document.getElementById("bewerkGebruikerModal"));
            bewerkModal.hide();
        })
        .catch(error => {
            console.error('Fout bij het bijwerken van de gebruiker:', error.message);
            // Voeg hier code toe om een foutmelding te tonen aan de gebruiker
        });
}


/// Functie om een gebruiker te verwijderen
function verwijderGebruiker(gebruikerId) {
    // Bevestiging vragen aan de gebruiker voordat een gebruiker wordt verwijderd
    var bevestigen = confirm("Weet je zeker dat je deze gebruiker wilt verwijderen?");

    if (bevestigen) {
        // URL voor het verwijderen van een gebruiker, aangepast met de gebruikerId
        var verwijderUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/gebruikers/' + gebruikerId;

        // HTTP DELETE-verzoek uitvoeren naar het eindpunt voor het verwijderen van een gebruiker
        fetch(verwijderUrl, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // Als het verwijderen succesvol is, vernieuw de gebruikerslijst
                    haalGebruikersOp();
                } else {
                    // Als er een fout optreedt, toon een melding aan de gebruiker
                    alert('Er is een fout opgetreden bij het verwijderen van de gebruiker.');
                }
            })
            .catch(error => {
                console.error('Er is een fout opgetreden bij het verwijderen van de gebruiker:', error);
                alert('Er is een fout opgetreden bij het verwijderen van de gebruiker.');
            });
    }
}


// Roep de functie aan om gebruikersgegevens op te halen wanneer de pagina geladen is
document.addEventListener('DOMContentLoaded', haalGebruikersOp);
