package ru.geekbrains.persist.repo;

// Товар (Product), с полями id, name, price.
// Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
// Репозиторий должен уметь выдавать список всех товаров и товар по id.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategoryId(Long id);
    List<Product> findByBrandId(Long id);
}