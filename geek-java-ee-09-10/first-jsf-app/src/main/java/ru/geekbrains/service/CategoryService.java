package ru.geekbrains.service;

import ru.geekbrains.persist.Brand;
import ru.geekbrains.persist.Category;
import ru.geekbrains.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll();

    Optional<CategoryDto> findById(long id);

    Category save(CategoryDto categoryDto);

    void delete(long id);

}
