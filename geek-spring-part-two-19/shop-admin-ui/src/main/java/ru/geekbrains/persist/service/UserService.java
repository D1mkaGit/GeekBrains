package ru.geekbrains.persist.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.model.SystemUser;
import ru.geekbrains.model.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
