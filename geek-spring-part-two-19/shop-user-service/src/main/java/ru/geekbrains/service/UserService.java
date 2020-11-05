package ru.geekbrains.service;

import ru.geekbrains.controller.repr.UserRepr;

import java.util.List;
import java.util.Optional;


public interface UserService {

    void save(UserRepr userRepr);

    void saveWithRoleLike(UserRepr userRepr, String roleLike);

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Long id);

    Optional<UserRepr> findOneByUsername(String username);

    void delete(Long id);
}
