package ru.geekbrains.controllers;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.services.BucketService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BucketController implements Serializable {

    @EJB
    private BucketService bucketService;

    public List<ProductDto> getAllProductInBucket(){
        return new ArrayList<>(bucketService.getAllProducts());
    }


    public String addToCart(ProductDto product) {
        bucketService.addToCart(product);
        return "/product.xhtml?faces-redirect-true";
    }


    public String deleteProduct(ProductDto product) {
        bucketService.deleteById(product.getId());
        return "/bucket.xhtml?faces-redirect-true";
    }
}
