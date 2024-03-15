package sr.unasat.bp24.hibernate.dto;

public class CategorieDTO {
    private int categorieId;
    private String naam;

    // Constructors
    public CategorieDTO() {
    }

    public CategorieDTO(int categorieId, String naam) {
        this.categorieId = categorieId;
        this.naam = naam;
    }

    // Getters and setters
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
}
