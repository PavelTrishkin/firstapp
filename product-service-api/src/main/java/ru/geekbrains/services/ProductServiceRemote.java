package ru.geekbrains.services;

import java.util.List;

public interface ProductServiceRemote {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();
}
