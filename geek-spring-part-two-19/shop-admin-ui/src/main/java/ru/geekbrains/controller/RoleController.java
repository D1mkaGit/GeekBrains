package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.repo.RoleRepository;


@Controller
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @GetMapping("/admin/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }

    @GetMapping("/admin/role/create")
    public String adminRoleCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @GetMapping("/admin/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(NotFoundException::new));
        return "role_form";
    }

    @DeleteMapping("/admin/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleRepository.deleteById(id);
        return "redirect:/admin/roles";
    }

    @PostMapping("/admin/role")
    public String adminUpsertRole(Model model, RedirectAttributes redirectAttributes, Role role) {
        model.addAttribute("activePage", "Roles");

        try {
            roleRepository.save(role);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating role", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (role.getId() == null) {
                return "redirect:/admin/role/create";
            }
            return "redirect:/admin/role/" + role.getId() + "/edit";
        }
        return "redirect:/admin/roles";
    }

}
