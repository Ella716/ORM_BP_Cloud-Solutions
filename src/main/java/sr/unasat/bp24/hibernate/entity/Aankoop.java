package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Aankoop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aankoopId;
    private Date datum;

    @ManyToOne
    @JoinColumn(name = "gebruiker_id")
    private Gebruiker gebruiker;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getAankoopId() {
        return aankoopId;
    }

    public void setAankoopId(int aankoopId) {
        this.aankoopId = aankoopId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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
