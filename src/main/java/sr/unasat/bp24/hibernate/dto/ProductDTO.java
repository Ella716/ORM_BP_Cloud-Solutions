package sr.unasat.bp24.hibernate.dto;

public class ProductDTO {
    private int productId;
    private String naam;
    private double prijs;
    private String categorieNaam;

    public ProductDTO() {

    }

    public ProductDTO(int productId, String naam, double prijs, String categorieNaam) {
        this.productId = productId;
        this.naam = naam;
        this.prijs = prijs;
        this.categorieNaam = categorieNaam;
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

    public String getCategorieNaam() {
        return categorieNaam;
    }

    public void setCategorieNaam(String categorieNaam) {
        this.categorieNaam = categorieNaam;
    }
}
