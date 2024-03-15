package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.dto.ProductDTO;
import sr.unasat.bp24.hibernate.entity.Product;
import sr.unasat.bp24.hibernate.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService() {
        this.repository = new ProductRepository(JPAConfiguration.getEntityManager());
    }

    public List<ProductDTO> getAllProductsWithCategory() {
        return repository.getAllProductsWithCategory();
    }

    public Product createProduct(Product product) {
        return repository.createProduct(product);
    }

    public Product getProductById(int productId) {
        return repository.getProductById(productId);
    }

    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
    }


    public List<Product> zoekProductenOpNaam(String naam) {
        // Roep de repository-methode aan die de zoekopdracht uitvoert
        return repository.zoekOpNaam(naam);
    }
}
