package ru.geekbrains.controllers;

import ru.geekbrains.persist.BucketRepository;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BucketController implements Serializable {

    @Inject
    BucketRepository bucketRepository;

    private Product product;

    public List<Product> getAllProductInBucket(){
        return bucketRepository.findAll();
    }


    public String addToCart(Product product) {
        bucketRepository.addToBucket(product);
        return "/bucket.xhtml?faces-redirect-true";
    }
}
