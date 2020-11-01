package ru.geekbrains.service;


import ru.geekbrains.controller.repr.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);
}
