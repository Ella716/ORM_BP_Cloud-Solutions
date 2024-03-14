package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gebruikerId;
    private String naam;
    @Column(unique = true)
    private String email;
    private String wachtwoord;

    @OneToMany(mappedBy = "gebruiker", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Beoordeling> beoordelingen;

    @OneToMany(mappedBy = "gebruiker", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Aankoop> aankopen;

    @ManyToMany
    @JoinTable(
            name = "gebruikerfavorieten",
            joinColumns = @JoinColumn(name = "gebruiker_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favorieteProducten;

    public Gebruiker(int gebruikerId, String naam, String email, String wachtwoord) {
        this.gebruikerId = gebruikerId;
        this.naam = naam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public Gebruiker() {

    }


    public int getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(int gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
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

    public List<Product> getFavorieteProducten() {
        return favorieteProducten;
    }

    public void setFavorieteProducten(List<Product> favorieteProducten) {
        this.favorieteProducten = favorieteProducten;
    }
}
