package sr.unasat.bp24.hibernate.factory;

import sr.unasat.bp24.hibernate.entity.Product;

public class ProductFactory {
    public static Product maakProduct(String naam, double prijs) {
        return new Product(naam, prijs);
    }
}
