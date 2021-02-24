package ru.geekbrains.persist;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class BucketRepository implements Serializable {

    private Map<Long, Product> products = new HashMap<>();

    public void addToBucket(Product product) {
        products.put(product.getId(), product);
    }

    public void removeFromBucket(Product product) {
        products.remove(product.getId());
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

}
