package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.dto.ProductDTO;
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

    public Product getProductById(int productId) {
        return entityManager.find(Product.class, productId);
    }

    public List<ProductDTO> getAllProductsWithCategory() {
        String query = "SELECT new sr.unasat.bp24.hibernate.dto.ProductDTO(p.id, p.naam, p.prijs, c.naam) " +
                "FROM Product p " +
                "JOIN p.categorie c";
        TypedQuery<ProductDTO> typedQuery = entityManager.createQuery(query, ProductDTO.class);
        return typedQuery.getResultList();
    }


    public void updateProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }

    public void deleteProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    public List<Product> zoekOpNaam(String naam) {
        String jpql = "SELECT p FROM Product p WHERE p.naam LIKE :naam";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("naam", "%" + naam + "%"); // Het '%' symbool staat voor wildcard en maakt gedeeltelijke overeenkomsten mogelijk
        return query.getResultList();
    }
}
