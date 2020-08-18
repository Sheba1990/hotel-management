package by.nikita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {

    @GetMapping
    public String homePage(Model model) {
        return "home/home";
    }
}
