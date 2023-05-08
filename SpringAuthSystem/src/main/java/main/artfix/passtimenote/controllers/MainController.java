package main.artfix.passtimenote.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/home")
    public String homeLinkHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "log";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}