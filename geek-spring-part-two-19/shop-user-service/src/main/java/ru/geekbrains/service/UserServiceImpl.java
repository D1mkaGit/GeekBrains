package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.repo.RoleRepository;
import ru.geekbrains.persist.repo.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void save(UserRepr userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setFirstName(userRepr.getFirstName());
        user.setLastName(userRepr.getLastName());
        user.setEmail(userRepr.getEmail());
        user.setRoles(userRepr.getRoles());
        userRepository.save(user);
    }

    @Override
    public void saveWithRoleLike(UserRepr userRepr, String roleName) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setFirstName(userRepr.getFirstName());
        user.setLastName(userRepr.getLastName());
        user.setEmail(userRepr.getEmail());
        user.setRoles(roleRepository.findByNameContains(roleName));
        userRepository.save(user);
    }


    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRepr> findById(Long id) {
        return userRepository.findById(id).map(UserRepr::new);
    }

    @Override
    public Optional<UserRepr> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username).map(UserRepr::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
