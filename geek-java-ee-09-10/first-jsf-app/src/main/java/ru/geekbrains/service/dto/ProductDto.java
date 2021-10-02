package ru.geekbrains.service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, BigDecimal price, Long categoryId, String categoryName, Long brandId, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.brandId = brandId;
        this.brandName = brandName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                price.equals(that.price) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(brandName, that.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, categoryId, categoryName, brandId, brandName);
    }
}
