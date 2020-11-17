package ru.geekbrains.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan(basePackages = "ru.geekbrains.chat")
public class MainController {

    @RequestMapping("/admin")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin-index";
    }

    @GetMapping("/admin/login")
    public String showMyLoginPage() {
        return "a-login";
    }
}
