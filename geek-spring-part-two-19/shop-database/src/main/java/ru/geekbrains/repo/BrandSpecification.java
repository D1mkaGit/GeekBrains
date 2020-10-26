package ru.geekbrains.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.model.Brand;

public class BrandSpecification {
    public static Specification<Brand> trueLiteral() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
