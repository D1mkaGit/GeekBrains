package ru.geekbrains.service;

import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.service.dto.ProductDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Named
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "Product 1", new BigDecimal(100), null));
            productRepository.save(new Product(null, "Product 2", new BigDecimal(200), null));
            productRepository.save(new Product(null, "Product 3", new BigDecimal(300), null));
            productRepository.save(new Product(null, "Продукт 4", new BigDecimal(300), null));
        }
    }

    @Transactional
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductService::convert)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id)
                .map(ProductService::convert);
    }

    @Transactional
    public Product save(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId())
        );
        return productRepository.save(product);
    }

    @Transactional
    public void delete(long id) {
        productRepository.delete(id);
    }

    public long count() {
        return productRepository.count();
    }

    private static ProductDto convert(Product prod) {
        return new ProductDto(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getCategory() != null ? prod.getCategory().getId() : null,
                prod.getCategory() != null ? prod.getCategory().getName() : null
        );
    }
}
