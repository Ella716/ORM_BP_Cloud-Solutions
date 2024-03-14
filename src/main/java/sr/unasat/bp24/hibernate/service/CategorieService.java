package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Categorie;
import sr.unasat.bp24.hibernate.repository.CategorieRepository;

import java.util.List;

public class CategorieService {
    private final CategorieRepository repository;

    public CategorieService() {
        this.repository = new CategorieRepository(JPAConfiguration.getEntityManager());
    }

    public List<Categorie> getCategorieen() {
        return repository.getCategorieen();
    }

    public Categorie createCategorie(String categorieNaam) {
        return repository.createCategorie(categorieNaam);
    }
    public Categorie updateCategorie(int categorieId, String newNaam) {
        Categorie categorie = repository.findById(categorieId);
        if (categorie == null) {
            return null;
        }
        categorie.setNaam(newNaam);
        return repository.save(categorie);
    }

    public boolean deleteCategorie(int categorieId) {
        Categorie categorie = repository.findById(categorieId);
        if (categorie == null) {
            return false;
        }
        return repository.delete(categorie);
    }


}
