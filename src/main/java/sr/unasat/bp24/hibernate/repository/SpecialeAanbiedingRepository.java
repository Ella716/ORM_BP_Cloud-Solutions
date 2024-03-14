package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import sr.unasat.bp24.hibernate.entity.SpecialeAanbieding;

import java.util.List;

public class SpecialeAanbiedingRepository {
    private EntityManager entityManager;

    public SpecialeAanbiedingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SpecialeAanbieding createSpecialeAanbieding(SpecialeAanbieding specialeAanbieding) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(specialeAanbieding);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return specialeAanbieding;
    }
}
