package ru.geekbrains.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ToString.Exclude // нужно для lombok, иначе падает редактирование с ошибкой: Product.toString() null и Category.toString() null
    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name = "products_pictures",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;
}