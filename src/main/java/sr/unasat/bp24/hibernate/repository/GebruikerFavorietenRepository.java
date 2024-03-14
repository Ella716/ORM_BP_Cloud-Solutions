package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import sr.unasat.bp24.hibernate.entity.GebruikerFavorieten;

import java.util.List;

public class GebruikerFavorietenRepository {
    private EntityManager entityManager;

    public GebruikerFavorietenRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GebruikerFavorieten createGebruikerFavorieten(GebruikerFavorieten gebruikerFavorieten) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(gebruikerFavorieten);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return gebruikerFavorieten;
    }
}
