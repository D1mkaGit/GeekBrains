package ru.geekbrains.persistance;

// Товар (Product), с полями id, title, cost.
// Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
// Репозиторий должен уметь выдавать список всех товаров и товар по id.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private Map<Integer, Product> productList;

    @Autowired
    public ProductRepository() {
        productList = new HashMap<>();
        setupDefaultProductList();
    }

    public Collection<Product> getAllProducts() {
        return productList.values();
    }

    public Product findById(int id) {
        return productList.get(id);
    }

    private void setupDefaultProductList() {
        addProduct("MacBook Air 13\"", "1000");
        addProduct("Lenovo T420 14\"", "700");
        addProduct("Lenovo T430 14\"", "900");
        addProduct("Lenovo T435 14\"", "930");
    }

    public void addProduct(String title, String price) {
        productList.put(getNextId(),new Product(getNextId(), title, price));
    }

    public int getNextId() {
       return productList.size();
    }

    public void updateProduct(Product product) {
        productList.put(product.getId(), product);
    }

    public void removeProductById(int id) {
        productList.remove(id);
    }
}
