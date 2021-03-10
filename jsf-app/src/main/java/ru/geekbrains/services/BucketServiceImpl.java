package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.BucketRepository;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;
import java.util.stream.Collectors;

@Stateful
public class BucketServiceImpl implements BucketService {

    @EJB
    private BucketRepository bucketRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public void deleteById(Long id) {
        bucketRepository.deleteById(id);
    }

    @Override
    public void addToCart(ProductDto product) {
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        bucketRepository.addToBucket(new Product(product, category));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return bucketRepository.findAll().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }


}
