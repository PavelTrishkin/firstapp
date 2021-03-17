package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();

    void saveOrUpdate(ProductDto product);

    void deleteById(Long id);

    Product findByName(String name);

    List<Product> findAllByCategoryId(Long id);
}
