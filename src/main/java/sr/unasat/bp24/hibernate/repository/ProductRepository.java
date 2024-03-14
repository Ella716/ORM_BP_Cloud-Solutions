package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.entity.Product;

import java.util.List;

public class ProductRepository {
    private EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product createProduct(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return product;
    }

    public List<Product> zoekOpNaam(String naam) {
        String jpql = "SELECT p FROM Product p WHERE p.naam LIKE :naam";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("naam", "%" + naam + "%"); // Het '%' symbool staat voor wildcard en maakt gedeeltelijke overeenkomsten mogelijk
        return query.getResultList();
    }
}
