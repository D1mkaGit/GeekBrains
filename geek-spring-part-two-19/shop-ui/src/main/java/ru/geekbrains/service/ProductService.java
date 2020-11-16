package ru.geekbrains.service;


import ru.geekbrains.controller.repr.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    List<ProductRepr> findByCategoryId(Long id);

    List<ProductRepr> findByBrandId(Long id);

    Optional<ProductRepr> findById(Long id);

    List<Long> findDistinctCategoryId();

    List<Long> findDistinctBrandId();
}
