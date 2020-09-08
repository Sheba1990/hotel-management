package by.nikita.controllers;

import by.nikita.dao.api.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @Autowired
    private IUserDao userDao;

    @GetMapping
    public String loginForm(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
            model.addAttribute("username", (((User) principal).getUsername()));
            model.addAttribute("user", userDao.getByUsername(username));
        }
        return "greeting/index";
    }
}
