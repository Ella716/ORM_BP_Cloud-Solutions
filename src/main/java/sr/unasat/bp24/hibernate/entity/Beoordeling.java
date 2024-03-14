package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

@Entity
public class Beoordeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int beoordelingId;
    private String opmerking;
    private int score;

    @ManyToOne
    @JoinColumn(name = "gebruiker_id")
    private Gebruiker gebruiker;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getBeoordelingId() {
        return beoordelingId;
    }

    public void setBeoordelingId(int beoordelingId) {
        this.beoordelingId = beoordelingId;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
