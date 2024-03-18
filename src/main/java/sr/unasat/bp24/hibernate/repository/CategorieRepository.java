package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.entity.Categorie;
import sr.unasat.bp24.hibernate.entity.Gebruiker;

import java.util.List;


public class CategorieRepository {
    private EntityManager entityManager;

    public CategorieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Categorie> getCategorieen() {
        String query = "SELECT NEW Categorie(c.categorieId, c.naam) FROM Categorie c";
        TypedQuery<Categorie> typedQuery = entityManager.createQuery(query, Categorie.class);
        return typedQuery.getResultList();
    }

    public Categorie createCategorie(String categorieNaam) {
        // Controleer of de categorie al bestaat in de database
        TypedQuery<Categorie> query = entityManager.createQuery(
                        "SELECT c FROM Categorie c WHERE c.naam = :naam", Categorie.class)
                .setParameter("naam", categorieNaam);

        List<Categorie> bestaandeCategorieen = query.getResultList();

        if (!bestaandeCategorieen.isEmpty()) {
            // Gebruik de bestaande categorie als deze al bestaat
            return bestaandeCategorieen.get(0);
        }

        // Als de categorie nog niet bestaat, maak een nieuwe categorie aan
        Categorie nieuweCategorie = new Categorie();
        nieuweCategorie.setNaam(categorieNaam);

        entityManager.persist(nieuweCategorie);

        return nieuweCategorie;
    }

    public Categorie findById(int categorieId) {
        return entityManager.find(Categorie.class, categorieId);
    }

    public Categorie save(Categorie categorie) {
        if (entityManager.contains(categorie)) {
            return entityManager.merge(categorie);
        } else {
            entityManager.persist(categorie);
            return categorie;
        }
    }

    public void updateCategorie(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.merge(categorie);
        entityManager.getTransaction().commit();
    }

    public boolean delete(Categorie categorie) {
        categorie = entityManager.merge(categorie);
        entityManager.remove(categorie);
        return true;
    }
}
