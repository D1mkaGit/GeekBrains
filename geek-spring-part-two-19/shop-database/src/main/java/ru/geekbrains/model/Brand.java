package ru.geekbrains.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Long id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "brand")
    List<Product> products;
}