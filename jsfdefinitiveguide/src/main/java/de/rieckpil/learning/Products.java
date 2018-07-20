package de.rieckpil.learning;

import de.rieckpil.learning.jsf.control.ProductsService;
import de.rieckpil.learning.jsf.entity.Product;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class Products implements Serializable {

    @Inject
    private ProductsService productsService;

    private List<Product> products;
    private Product product = new Product();

    @PostConstruct
    public void init() {
        this.products = productsService.list();
    }

    public void save() {
        productsService.update(products);
    }

    public void add() {
        productsService.create(product);
        products.add(0, product);
        product = new Product();
    }

    public void delete(Product product) {
        productsService.delete(product);
        products.remove(product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductsService getProductsService() {
        return productsService;
    }

    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
