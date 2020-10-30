package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.model.Role;
import ru.geekbrains.repo.RoleRepository;


@Controller
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @GetMapping("/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }

    @GetMapping("/role/create")
    public String adminRoleCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @GetMapping("/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(NotFoundException::new));
        return "role_form";
    }

    @DeleteMapping("/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }

    @PostMapping("/role")
    public String adminUpsertRole(Model model, RedirectAttributes redirectAttributes, Role role) {
        model.addAttribute("activePage", "Roles");

        try {
            roleRepository.save(role);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating role", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (role.getId() == null) {
                return "redirect:/role/create";
            }
            return "redirect:/role/" + role.getId() + "/edit";
        }
        return "redirect:/roles";
    }

}
