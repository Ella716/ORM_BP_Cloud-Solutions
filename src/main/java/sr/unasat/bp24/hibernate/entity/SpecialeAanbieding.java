package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

@Entity
public class SpecialeAanbieding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialeAanbiedingId;
    private String aanbiedingTekst;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product speciaalProduct;

    public int getSpecialeAanbiedingId() {
        return specialeAanbiedingId;
    }

    public void setSpecialeAanbiedingId(int specialeAanbiedingId) {
        this.specialeAanbiedingId = specialeAanbiedingId;
    }

    public String getAanbiedingTekst() {
        return aanbiedingTekst;
    }

    public void setAanbiedingTekst(String aanbiedingTekst) {
        this.aanbiedingTekst = aanbiedingTekst;
    }

    public Product getSpeciaalProduct() {
        return speciaalProduct;
    }

    public void setSpeciaalProduct(Product speciaalProduct) {
        this.speciaalProduct = speciaalProduct;
    }

}
