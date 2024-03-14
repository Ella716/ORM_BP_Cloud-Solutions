package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.entity.Beoordeling;

import java.util.List;

public class BeoordelingRepository {
    private EntityManager entityManager;

    public BeoordelingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Beoordeling> getBeoordelingen() {
        String query = "select b from Beoordeling b";
        TypedQuery<Beoordeling> typedQuery = entityManager.createQuery(query, Beoordeling.class);
        return typedQuery.getResultList();
    }

    public Beoordeling createBeoordeling(Beoordeling beoordeling) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(beoordeling);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return beoordeling;
    }}
