package ru.geekbrains.service;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;
import ru.geekbrains.service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@PermitAll
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

    @TransactionAttribute
    public void saveOrUpdate(UserDto user) {
        User saved = userRepository.saveOrUpdate(new User(user));
        user.setId(saved.getId());
    }

    @TransactionAttribute
    public void delete(long id) {
        userRepository.delete(id);
    }

    @TransactionAttribute
    public UserDto findById(int id) {
        return new UserDto(userRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(int id) {
        return userRepository.findById(id) != null;
    }

    @TransactionAttribute
    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

}
