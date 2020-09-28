package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.User;

public final class UserSpecification {

    public static Specification<User> trueLiteral() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    public static Specification<User> loginLike(String login) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("login"), "%" + login + "%");
    }

    public static Specification<User> emailLike(String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }
}
