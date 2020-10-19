package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class WelcomeController {

    @GetMapping
    public  String welcomePage(){
        return "welcome";
    }


    @GetMapping("/login")
    public String showMyLoginPage() {
        return "myLogin";
    }

}
