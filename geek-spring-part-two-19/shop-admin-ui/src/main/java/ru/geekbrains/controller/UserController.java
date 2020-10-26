package ru.geekbrains.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.User;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.repo.UserRepository;
import ru.geekbrains.repo.UserSpecification;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String allUsers(Model model,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size
                           ) {
        logger.info("Filtering by name: {} email: {}", username, email);

        Specification<User> spec = UserSpecification.trueLiteral();

        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(5));
        int totalPages = userRepository.findAll(spec, pageRequest).getTotalPages();

        if (username != null && !username.isEmpty()){
            spec = spec.and(UserSpecification.userNameLike(username));
        }

        if (email != null && !email.isEmpty()){
            spec = spec.and(UserSpecification.userNameLike(email));
        }

        model.addAttribute("usersPage", userRepository.findAll(spec, pageRequest));
        model.addAttribute("totalPages", totalPages);
        return "users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User"));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }

        // TODO реализовать проверку повторного ввода пароля.
        // TODO Исправить метод bindingResult.rejectValue();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
