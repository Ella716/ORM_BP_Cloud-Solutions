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

function toonBericht(type, title, message) {
    const toast = new bootstrap.Toast(document.getElementById('message'));
    document.getElementById('message-title').textContent = title;
    document.getElementById('message-body').textContent = message;
    document.getElementById('message').classList.remove('bg-success', 'bg-danger');
    document.getElementById('message').classList.add(`bg-${type}`);
    toast.show();
}

function toonSuccesBericht(title, message) {
    toonBericht('success', title, message);
}

function toonFoutBericht(title, message) {
    toonBericht('danger', title, message);
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
    constructor(gebruikerId, naam, email, wachtwoord) {
        this.gebruikerId = gebruikerId;
        this.naam = naam;
        this.email = email;
        this.wachtwoord = wachtwoord;

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
            toonSuccesBericht('Gebruiker succesvol bijgewerkt', data.message);


            // Sluit de bewerkingsmodal
            const modal = new bootstrap.Modal(document.getElementById('bewerkGebruikerModal'));
            modal.hide();

            //Update the table by calling haalGebruikersOp()
            haalGebruikersOp();
        })
        .catch(error => {
            console.error('Fout bij het bijwerken van de gebruiker:', error.message);
            // Voeg hier code toe om een foutmelding te tonen aan de gebruiker
            toonFoutBericht('Fout bij het bijwerkenvan de gebruiker', error.message);

        });
}

function toonToevoegModal() {


    // Event listener toevoegen aan de knop "Toevoegen"
    document.getElementById('toevoegenKnop').addEventListener('click', function() {
        // Gebruikersgegevens ophalen uit de invoervelden
        var nieuweGebruiker = new GebruikerDTO(document.getElementById('nieuweId').value,document.getElementById('nieuweNaam').value, document.getElementById('nieuweEmail').value, document.getElementById('nieuwWachtwoord').value);

        // Log the 'nieuweGebruiker' object to the console
        console.log('Nieuwe gebruiker:', nieuweGebruiker);

        // Verstuur de nieuwe gebruiker naar de backend om op te slaan
        toevoegGebruikerInBackend(nieuweGebruiker);
    });
}

function toevoegGebruikerInBackend(nieuweGebruiker) {
    // Log the 'nieuweGebruiker' object to the console
    console.log('Nieuwe gebruiker:', nieuweGebruiker);

    // Endpoint voor het toevoegen van een gebruiker in de backend
    var endpoint ='http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/gebruikers';

    // Log the endpoint URL to the console
    console.log('Endpoint URL:', endpoint);

    // Opties voor het HTTP-verzoek
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(nieuweGebruiker)
    };

    // HTTP-verzoek uitvoeren met behulp van Fetch API
    fetch(endpoint, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Er is een fout opgetreden bij het toevoegen van de gebruiker.');
            }
            return response.json();
        })
        .then(data => {
            // Verwerken van de respons van de backend, bijvoorbeeld notificatie weergeven
            console.log('Gebruiker succesvol toegevoegd:', data);
            // Voeg hier code toe om een succesmelding te tonen aan de gebruiker
            toonSuccesBericht('Gebruiker succesvol toegevoegd', data.message);



            //Update the table by calling haalGebruikersOp()
            haalGebruikersOp();
        })
        .catch(error => {
            console.error('Fout bij het toevoegen van de gebruiker:', error.message);
            // Voeg hier code toe om een foutmelding te tonen aan de gebruiker
            toonFoutBericht('Fout bij het toevoegen van de gebruiker', error.message);

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
                    toonSuccesBericht('Gebruiker succesvol verwijderd.');

                } else {
                    // Als er een fout optreedt, toon een melding aan de gebruiker
                    toonFoutBericht('Er is een fout opgetreden bij het verwijderen van de gebruiker.');
                }
            })
            .catch(error => {
                console.error('Er is een fout opgetreden bij het verwijderen van de gebruiker:', error);
                toonFoutBericht('Er is een fout opgetreden bij het verwijderen van de gebruiker.');
            });
    }
}


// Roep de functie aan om gebruikersgegevens op te halen wanneer de pagina geladen is
document.addEventListener('DOMContentLoaded', haalGebruikersOp);
