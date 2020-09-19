package ru.geekbrains.persist.repo;

// Товар (Product), с полями id, title, cost.
// Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
// Репозиторий должен уметь выдавать список всех товаров и товар по id.

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findByCostLessThan(BigDecimal maxPrice, Pageable pageable);

    Page<Product> findByCostGreaterThan(BigDecimal minPrice, Pageable pageable);

    Page<Product> findByCostBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

}
