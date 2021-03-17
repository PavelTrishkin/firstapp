package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BucketService {

    void deleteById(Long id);

    void addToCart(ProductDto product);

    List<ProductDto> getAllProducts();
}
