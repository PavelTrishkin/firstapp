package ru.geekbrains.lesson2.listener;

import ru.geekbrains.lesson2.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("productRepository", initProductRepository());
        sce.getServletContext().setAttribute("userRepository", initUserRepository());
        sce.getServletContext().setAttribute("categoryRepository", initCategoryRepository());
    }

    private ProductRepository initProductRepository(){
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        return productRepository;
    }

    private UserRepository initUserRepository(){
        UserRepository userRepository = new UserRepository();

        userRepository.saveOrUpdate(new User(null, "Petr", "petr@mail.ru"));
        userRepository.saveOrUpdate(new User(null, "Pavel", "pavel@mail.ru"));
        userRepository.saveOrUpdate(new User(null, "Ivan", "ivan@mail.ru"));

        return userRepository;
    }

    private CategoryRepository initCategoryRepository(){
        CategoryRepository categoryRepository = new CategoryRepository();

        categoryRepository.saveOrUpdate(new Category(null, "Fruit"));
        categoryRepository.saveOrUpdate(new Category(null, "Vegetables"));
        categoryRepository.saveOrUpdate(new Category(null, "Appliances"));

        return categoryRepository;
    }
}
