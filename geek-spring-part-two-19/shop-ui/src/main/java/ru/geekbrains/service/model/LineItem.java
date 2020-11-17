package ru.geekbrains.service.model;

import ru.geekbrains.controller.repr.ProductRepr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LineItem implements Serializable {

    private Long productId;
    private ProductRepr productRepr;
    private BigDecimal bidPrice;

    public LineItem(ProductRepr productRepr, BigDecimal bidPrice) {
        this.productId = productRepr.getId();
        this.productRepr = productRepr;
        this.bidPrice = bidPrice;
    }

    public LineItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductRepr getProductRepr() {
        return productRepr;
    }

    public void setProductRepr(ProductRepr productRepr) {
        this.productRepr = productRepr;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LineItem lineItem = (LineItem) obj;
        return productId.equals(lineItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, bidPrice);
    }
}
