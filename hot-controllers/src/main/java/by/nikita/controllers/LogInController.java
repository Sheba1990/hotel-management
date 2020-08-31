package by.nikita.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LogInController {

    @GetMapping
    public String logInForm(Model model) {
        return "/signIn_signUp/login";
    }

    @PostMapping
    public String logIn(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            model.addAttribute("username", ((User) principal).getUsername());
        } else {
            return "redirect:/login";
        }
        return "redirect:/greeting";
    }

}
