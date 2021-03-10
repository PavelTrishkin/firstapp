package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemote {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();
}
