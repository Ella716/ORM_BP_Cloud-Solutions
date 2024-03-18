package sr.unasat.bp24.hibernate.service;

import jakarta.persistence.EntityManager;
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
    public Categorie updateCategorie(Categorie categorie) {
        Categorie existingCategorie = repository.findById(categorie.getCategorieId());
        if (existingCategorie == null) {
            return null;
        }

        // Update the name of the existing category
        existingCategorie.setNaam(categorie.getNaam());

        // Update the associated products of the existing category
        // existingCategorie.setProducten(categorie.getProducten()); // Uncomment this line if you want to update the associated products as well

        EntityManager em = JPAConfiguration.getEntityManager();
        em.getTransaction().begin();
        Categorie updatedCategorie = repository.save(existingCategorie);
        em.getTransaction().commit();

        return updatedCategorie;
    }


    public boolean deleteCategorie(int categorieId) {
        Categorie categorie = repository.findById(categorieId);
        if (categorie == null) {
            return false;
        }
        return repository.delete(categorie);
    }

    public Categorie getCategorieById(int categorieId) {
        return repository.findById(categorieId);
    }


}
