package de.rieckpil.learning.jsf.control;

import de.rieckpil.learning.jsf.entity.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Singleton
public class ProductsService {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @TransactionAttribute(REQUIRED)
    public void init() {

        Product p1 = new Product();
        p1.setDescription("A football");
        p1.setName("Football");

        Product p2 = new Product();
        p2.setDescription("A basket");
        p2.setName("Basket");

        entityManager.persist(p1);
        entityManager.persist(p2);
    }

    @TransactionAttribute(REQUIRED)
    public void update(Iterable<Product> products) {
        products.forEach(entityManager::merge);
    }

    public List<Product> list() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public Long create(Product product) {
        entityManager.persist(product);
        return product.getId();
    }

    @TransactionAttribute(REQUIRED)
    public void delete(Product product) {
        
        if (entityManager.contains(product)) {
            entityManager.remove(product);
        } else {
            Product managedProduct = entityManager.find(Product.class, product.getId());
            if (managedProduct != null) {
                entityManager.remove(managedProduct);
            }
        }
    }
}
