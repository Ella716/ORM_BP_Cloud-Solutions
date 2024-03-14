package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.entity.Aankoop;

import java.util.List;

public class AankoopRepository {
    private EntityManager entityManager;

    public AankoopRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Aankoop> getAankopen() {
        String query = "select a from Aankoop a";
        TypedQuery<Aankoop> typedQuery = entityManager.createQuery(query, Aankoop.class);
        return typedQuery.getResultList();
    }

    public Aankoop createAankoop(Aankoop aankoop) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(aankoop);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return aankoop;
    }
}
