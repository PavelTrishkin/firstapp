package ru.geekbrains.persist;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class BucketRepository {

    private Map<Long, Product> products = new HashMap<>();

    public void addToBucket(Product product) {
        products.put(product.getId(), product);
    }

    public void removeFromBucket(Long id) {
        products.remove(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

}
