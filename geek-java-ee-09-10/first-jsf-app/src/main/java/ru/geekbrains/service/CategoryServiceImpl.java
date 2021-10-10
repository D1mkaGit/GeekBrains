package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.rest.CategoryResource;
import ru.geekbrains.service.dto.CategoryDto;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class CategoryServiceImpl implements CategoryService, CategoryResource {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAllWithProducts().stream()
                .map(CategoryServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDto> findById(long id) {
        return categoryRepository.findById(id)
                .map(CategoryServiceImpl::convert);
    }

    public CategoryDto findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @TransactionAttribute
    public Category save(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
        return categoryRepository.save(category);
    }

    public CategoryDto update(CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Brand");
        }
        Category saved = this.save(categoryDto);
        return new CategoryDto(
                saved.getId(),
                saved.getName()
        );
    }

    public CategoryDto insert(CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            throw new RuntimeException("Id should be null for new Brand");
        }
        Category saved = this.save(categoryDto);
        return new CategoryDto(
                saved.getId(),
                saved.getName()
        );
    }

    @TransactionAttribute
    public void delete(long id) {
        categoryRepository.delete(id);
    }

    private static CategoryDto convert(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getProducts().stream()
                        .map(ProductServiceImpl::convert)
                        .collect(Collectors.toList())
        );
    }
}
