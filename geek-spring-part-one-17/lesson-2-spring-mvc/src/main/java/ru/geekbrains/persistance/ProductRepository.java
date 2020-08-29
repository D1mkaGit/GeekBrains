package ru.geekbrains.persistance;

// Товар (Product), с полями id, title, cost.
// Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
// Репозиторий должен уметь выдавать список всех товаров и товар по id.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList;

    @Autowired
    public ProductRepository() {
        productList = new ArrayList<>();
        setupDefaultProductList();
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product findById(int id) {
        return productList.get(id);
    }

    private void setupDefaultProductList() {
        productList.add(new Product(getNextId(), "MacBook Air 13\"", "1000"));
        productList.add(new Product(getNextId(), "Lenovo T420 14\"", "700"));
        productList.add(new Product(getNextId(), "Lenovo T430 14\"", "900"));
        productList.add(new Product(getNextId(), "Lenovo T435 14\"", "930"));
    }

    public void addProduct(String title, String price) {
        productList.add(new Product(getNextId(), title, price));
    }

    public int getNextId() {
       return productList.size();
    }

    public void updateProduct(Product product) {
        productList.set(product.getId(), product);
    }

    public void removeProductById(int id) {
        productList.remove(id);
    }
}
