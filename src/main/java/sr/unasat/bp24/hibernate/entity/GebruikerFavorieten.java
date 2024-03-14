package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

@Entity
public class GebruikerFavorieten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favorietenId;

    @ManyToOne
    @JoinColumn(name = "gebruiker_id")
    private Gebruiker gebruiker;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product favorieteProduct;

    public int getFavorietenId() {
        return favorietenId;
    }

    public void setFavorietenId(int favorietenId) {
        this.favorietenId = favorietenId;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Product getFavorieteProduct() {
        return favorieteProduct;
    }

    public void setFavorieteProduct(Product favorieteProduct) {
        this.favorieteProduct = favorieteProduct;
    }


}
