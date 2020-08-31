package by.nikita.controllers;

import by.nikita.dto.UserDto;
import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/get/{id}")
    public ModelAndView getUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = userService.getUserById(id);
        modelAndView.setViewName("/views/users/user");
        modelAndView.addObject("user", user);
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
    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getAllUsers();
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

}
