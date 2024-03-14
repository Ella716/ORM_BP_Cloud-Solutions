package sr.unasat.bp24.hibernate.dto;

public class GebruikerDTO {
    private int gebruikerId;
    private String naam;
    private String email;

    public GebruikerDTO() {

    }

    public GebruikerDTO(int gebruikerId, String naam, String email) {
        this.gebruikerId = gebruikerId;
        this.naam = naam;
        this.email = email;
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
}
