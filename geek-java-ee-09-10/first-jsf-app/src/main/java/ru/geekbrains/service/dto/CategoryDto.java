package ru.geekbrains.service.dto;

import ru.geekbrains.persist.Product;

import java.util.List;
import java.util.Objects;

public class CategoryDto {

    private Long id;

    private String name;

    private List<Product> products;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto brandDto = (CategoryDto) o;
        return id.equals(brandDto.id) &&
                name.equals(brandDto.name) &&
                Objects.equals(products, brandDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}
