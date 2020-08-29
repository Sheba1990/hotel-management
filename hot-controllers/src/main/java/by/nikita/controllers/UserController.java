package by.nikita.controllers;

import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    public ModelAndView getUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping(value = "/edit_user/{user}")
    public ModelAndView showUserEditForm(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", Role.values());
        modelAndView.setViewName("user_edit");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ModelAndView userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        ModelAndView modelAndView = new ModelAndView();
        userService.addUser(user, username, form);
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }


}
