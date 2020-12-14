package ru.geekbrains.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "brands")
public class Brand implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @ToString.Exclude // нужно для lombok, иначе падает редактирование с ошибкой: Brand.toString() null
    @OneToMany(mappedBy = "brand",
            cascade = CascadeType.ALL)
    List<Product> products;

    public Brand(String name) {
        this.name = name;
    }
}