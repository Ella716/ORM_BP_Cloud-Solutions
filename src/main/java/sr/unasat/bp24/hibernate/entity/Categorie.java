package sr.unasat.bp24.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categorieId;
    @Column(unique = true)
    private String naam;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> producten;

    public Categorie(int categorieId, String naam) {
        this.categorieId = categorieId;
        this.naam = naam;
    }

    public Categorie() {

    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "categorieId=" + categorieId +
                ", naam='" + naam + '\'' +
                '}';
    }
}
