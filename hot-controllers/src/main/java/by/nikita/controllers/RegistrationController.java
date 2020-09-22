package by.nikita.controllers;

import by.nikita.dto.UserDto;
import by.nikita.services.api.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IRegistrationService registrationService;

    @GetMapping
    public String registrationForm(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "/signIn_signUp/registration";
    }

    @GetMapping("/{username}")
    public String activateForm(@PathVariable String username) {
        registrationService.activateUser(username);
        return "redirect:/login";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/signIn_signUp/registration";
        }
        registrationService.registerUser(userDto);
        return "redirect:/login";
    }

}
