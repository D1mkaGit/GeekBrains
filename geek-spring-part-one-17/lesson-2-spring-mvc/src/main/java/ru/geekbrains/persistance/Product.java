package ru.geekbrains.persistance;

// 2. Создать класс Товар (Product), с полями id, title, cost.
// ProductsRepository.class:
// Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
// Репозиторий должен уметь выдавать список всех товаров и товар по id.

import java.math.BigDecimal;

public class Product {
    private int id;
    private String title;
    private BigDecimal cost;

    public Product(int id, String title, String cost) {
        this.id = id;
        this.title = title;
        this.cost = new BigDecimal(cost);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCost() {
        return cost.toPlainString();
    }
}
