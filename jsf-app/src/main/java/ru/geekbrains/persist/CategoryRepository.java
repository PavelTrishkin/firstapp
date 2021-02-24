package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CategoryRepository implements Serializable {

    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    void init(){
        this.saveOrUpdate(new Category(null, "Fruit"));
        this.saveOrUpdate(new Category(null, "Vegetables"));
        this.saveOrUpdate(new Category(null, "Appliances"));
    }

    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            Long id = identity.incrementAndGet();
            category.setId(id);
        }
        categoryMap.put(category.getId(), category);
    }

    public void deleteById(Long id) {
        categoryMap.remove(id);
    }
}
