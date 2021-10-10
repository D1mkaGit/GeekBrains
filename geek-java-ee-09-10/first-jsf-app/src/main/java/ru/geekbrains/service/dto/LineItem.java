package ru.geekbrains.service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class LineItem {

    private ProductDto product;

    private final Long productId;

    private Integer qty;

    private BigDecimal price;

    public LineItem(ProductDto product, Integer qty) {
        this.product = product;
        this.qty = qty;
        this.price = product.getPrice();
        this.productId = product.getId();
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
