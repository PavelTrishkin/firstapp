package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;


    @PostConstruct
    void init(){
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Category(null, "Fruit"));
                saveOrUpdate(new Category(null, "Vegetables"));
                saveOrUpdate(new Category(null, "Appliances"));
                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                try {
                    ut.rollback();
                } catch (SystemException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategory", Category.class)
                .getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategory", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteCategoryById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
