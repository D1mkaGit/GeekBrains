package ru.geekbrains.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findOneByUsername(String userName);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByUsername(String name);

    Optional<User> findUserByEmail(String email);

}
