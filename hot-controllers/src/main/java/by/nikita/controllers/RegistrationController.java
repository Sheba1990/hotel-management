package by.nikita.controllers;

import by.nikita.dto.UserDto;
import by.nikita.services.api.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IRegistrationService registrationService;

    @GetMapping
    public String registrationForm() {
        return "/signIn_signUp/registration";
    }

    @GetMapping("/activate/{username}")
    public String activateForm(@PathVariable String username) {
        registrationService.activateUser(username);
        return "redirect:/login";
    }

    @PostMapping
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationService.registerUser(userDto);
        return "redirect:/login";
    }

}
