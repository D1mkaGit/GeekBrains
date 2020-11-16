package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.repo.ProductRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductRepr> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductRepr> findByBrandId(Long id) {
        return productRepository.findByBrandId(id).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Override
    @Transactional
    public List<Long> findDistinctCategoryId() {
        return productRepository
                .findAll()
                .stream()
                .map(p -> Long.valueOf(p.getCategory().getId()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Long> findDistinctBrandId() {
        return productRepository
                .findAll()
                .stream()
                .map(p -> Long.valueOf(p.getBrand().getId()))
                .distinct()
                .collect(Collectors.toList());
    }
}
