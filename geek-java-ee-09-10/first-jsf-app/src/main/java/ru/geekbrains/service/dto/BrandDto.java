package ru.geekbrains.service.dto;

import java.util.List;
import java.util.Objects;

public class BrandDto {

    private Long id;

    private String name;

    private List<ProductDto> products;

    public BrandDto() {
    }

    public BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandDto(Long id, String name, List<ProductDto> products) {
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

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandDto brandDto = (BrandDto) o;
        return id.equals(brandDto.id) &&
                name.equals(brandDto.name) &&
                Objects.equals(products, brandDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}
