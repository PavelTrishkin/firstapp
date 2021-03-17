package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();

    void saveOrUpdate(ProductDto product);

    void deleteById(Long id);
}
