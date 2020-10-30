package ru.geekbrains.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.service.UserService;
import ru.geekbrains.repo.RoleRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final RoleRepository roleRepository;

    private final UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository,
                          UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_form";
    }

    @GetMapping("/user/create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserRepr());
        model.addAttribute("roles", roleRepository.findAll());
        return "user_form";
    }

    @PostMapping("/user")
    public String adminUpsertUser(@Valid UserRepr user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "user_form";
        }

        userService.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}/delete")
    public String adminDeleteUser(Model model, @PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
//
//    @GetMapping("/roles")
//    public String adminRolesPage(Model model) {
//        model.addAttribute("activePage", "Roles");
//        return "index";
//    }
}
