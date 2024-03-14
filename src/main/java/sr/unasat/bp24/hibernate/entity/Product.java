package sr.unasat.bp24.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String naam;
    private double prijs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @OneToMany(mappedBy = "product")
    private List<Beoordeling> beoordelingen;

    @OneToMany(mappedBy = "product")
    private List<Aankoop> aankopen;

    @ManyToMany(mappedBy = "favorieteProducten")
    private List<Gebruiker> favorieteGebruikers;

    @OneToOne(mappedBy = "speciaalProduct")
    private SpecialeAanbieding specialeAanbieding;

    public Product(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Beoordeling> getBeoordelingen() {
        return beoordelingen;
    }

    public void setBeoordelingen(List<Beoordeling> beoordelingen) {
        this.beoordelingen = beoordelingen;
    }

    public List<Aankoop> getAankopen() {
        return aankopen;
    }

    public void setAankopen(List<Aankoop> aankopen) {
        this.aankopen = aankopen;
    }

    public List<Gebruiker> getFavorieteGebruikers() {
        return favorieteGebruikers;
    }

    public void setFavorieteGebruikers(List<Gebruiker> favorieteGebruikers) {
        this.favorieteGebruikers = favorieteGebruikers;
    }

    public SpecialeAanbieding getSpecialeAanbieding() {
        return specialeAanbieding;
    }

    public void setSpecialeAanbieding(SpecialeAanbieding specialeAanbieding) {
        this.specialeAanbieding = specialeAanbieding;
    }
}
