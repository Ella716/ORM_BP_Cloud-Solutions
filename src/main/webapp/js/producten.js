// URL van de REST-service voor het ophalen van productgegevens
const productenUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/producten';

// Functie om productgegevens op te halen via een HTTP GET-verzoek
function haalProductenOp() {
    fetch(productenUrl)
        .then(response => response.json())
        .then(data => {
            // Roep de functie aan om de productgegevens in de tabel weer te geven
            toonProducten(data);
        })
        .catch(error => console.error('Er is een fout opgetreden bij het ophalen van productgegevens:', error));
}

// Functie om productgegevens in de tabel weer te geven
function toonProducten(producten) {
    const productentabel = document.getElementById('producten-list');

    // Wis alle bestaande rijen in de tabel
    productentabel.innerHTML = '';

    // Voeg elke product toe aan de tabel als een rij
    producten.forEach(product => {
        const rij = productentabel.insertRow();

        // Voeg cellen toe voor elke eigenschap van het product
        const idCel = rij.insertCell();
        idCel.textContent = product.productId;

        const naamCel = rij.insertCell();
        naamCel.textContent = product.naam;

        const prijsCel = rij.insertCell();
        prijsCel.textContent = product.prijs;

        const categorieCel = rij.insertCell();
        categorieCel.textContent = product.categorieNaam; // Display the category name

        // Voeg bewerkings- en verwijderingsknoppen toe met respectievelijke functies
        const actieCel = rij.insertCell();
        const bewerkKnop = document.createElement('button');
        bewerkKnop.textContent = 'Bewerken';
        bewerkKnop.classList.add('btn', 'btn-primary', 'me-2'); // Voeg Bootstrap-klassen toe
        bewerkKnop.addEventListener('click', () => bewerkProduct(product));
        actieCel.appendChild(bewerkKnop);

        const verwijderKnop = document.createElement('button');
        verwijderKnop.textContent = 'Verwijderen';
        verwijderKnop.classList.add('btn', 'btn-danger'); // Voeg Bootstrap-klassen toe
        verwijderKnop.addEventListener('click', () => verwijderProduct(product.productId));
        actieCel.appendChild(verwijderKnop);
    });
}

class ProductDTO {
    constructor(productId, naam, prijs, categorieNaam) {
        this.productId = productId;
        this.naam = naam;
        this.prijs = prijs;
        this.categorieNaam = categorieNaam;
    }
}


// Functie om een product te bewerken
function bewerkProduct(product) {
    // Modal venster openen (using Bootstrap's JavaScript)
    const bewerkModal = new bootstrap.Modal(document.getElementById("bewerkProductModal"));
    bewerkModal.show();

    // Invullen van de invoervelden met productgegevens
    document.getElementById('product-naam').value = product.naam;
    document.getElementById('product-prijs').value = product.prijs;

    // Log the 'product' object to the console
    console.log('Product:', product);

    // Event listener toevoegen aan de knop "Opslaan"
    document.getElementById('opslaanProductKnop').addEventListener('click', function() {
        // Productgegevens ophalen uit de invoervelden
        var bewerktProduct = new ProductDTO(product.productId, document.getElementById('product-naam').value, document.getElementById('product-prijs').value, product.categorieId);

        // Log the 'bewerktProduct' object to the console
        console.log('Bewerkt product:', bewerktProduct);

        // Verstuur het bewerkte product naar de backend om op te slaan
        bewerkProductInBackend(bewerktProduct);
    });

    console.log('Product bewerken:', product);
}

// Functie om een bewerkt product naar de backend te sturen
function bewerkProductInBackend(bewerktProduct) {
    // Log the 'bewerktProduct' object to the console
    console.log('Bewerkt product:', bewerktProduct);

    // Endpoint voor het bewerken van een product in de backend
    var endpoint = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/producten/' + bewerktProduct.productId;

    // Log the endpoint URL to the console
    console.log('Endpoint URL:', endpoint);

    // Opties voor het HTTP-verzoek
    var options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bewerktProduct)
    };

    // HTTP-verzoek uitvoeren met behulp van Fetch API
    fetch(endpoint, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Er is een fout opgetreden bij het bijwerken van het product.');
            }
            return response.json();
        })
        .then(data => {
            // Verwerken van de respons van de backend, bijvoorbeeld notificatie weergeven
            console.log('Product succesvol bijgewerkt:', data);
            // Voeg hier code toe om een succesmelding te tonen aan de gebruiker

            // Sluit de bewerkingsmodal
            const bewerkModal = new bootstrap.Modal(document.getElementById("bewerkProductModal"));
            bewerkModal.hide();
        })
        .catch(error => {
            console.error('Fout bij het bijwerken van het product:', error.message);
            // Voeg hier code toe om een foutmelding te tonen aan de gebruiker
        });
}

/// Functie om een product te verwijderen
function verwijderProduct(productId) {
    // Bevestiging vragen aan de gebruiker voordat een product wordt verwijderd
    var bevestigen = confirm("Weet je zeker dat je dit product wilt verwijderen?");

    if (bevestigen) {
        // URL voor het verwijderen van een product, aangepast met de productId
        var verwijderUrl = 'http://localhost:8080/ORM_BP_24___Cloud_Solutions_war_exploded/api/producten/' + productId;

        // HTTP DELETE-verzoek uitvoeren naar het eindpunt voor het verwijderen van een product
        fetch(verwijderUrl, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // Als het verwijderen succesvol is, vernieuw de productenlijst
                    haalProductenOp();
                } else {
                    // Als er een fout optreedt, toon een melding aan de gebruiker
                    alert('Er is een fout opgetreden bij het verwijderen van het product.');
                }
            })
            .catch(error => {
                console.error('Er is een fout opgetreden bij het verwijderen van het product:', error);
                alert('Er is een fout opgetreden bij het verwijderen van het product.');
            });
    }
}

// Roep de functie aan om productgegevens op te halen wanneer de pagina geladen is
document.addEventListener('DOMContentLoaded', haalProductenOp);
