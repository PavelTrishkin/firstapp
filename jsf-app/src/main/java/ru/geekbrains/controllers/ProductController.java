package ru.geekbrains.controllers;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.services.ProductService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    private ProductDto product;

    private List<ProductDto> products;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productService.findAll();
        categories = categoryRepository.findAll();
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto product) {
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getCategories() {
        return categories;
    }
}
