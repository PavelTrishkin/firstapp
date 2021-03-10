package ru.geekbrains.persist;


import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class BucketRepository implements Serializable {

    private Map<Long, Product> products = new HashMap<>();

    public void addToBucket(Product product) {
        products.put(product.getId(), product);
    }

    public void deleteById(Long id) {
        products.remove(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

}
