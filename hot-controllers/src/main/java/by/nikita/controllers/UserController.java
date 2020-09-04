package by.nikita.controllers;

import by.nikita.dto.*;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/get/{id}")
    public ModelAndView getUserById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = userService.getUserById(id);
        modelAndView.setViewName("/views/users/user");
        modelAndView.addObject("user", user);
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

    @GetMapping(value = "/first_name/{firstName}")
    public ModelAndView getUsersByUserFirstName(@RequestParam String firstName) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByUserFirstName(firstName);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/last_name/{lastName}")
    public ModelAndView getUsersByUserLastName(@RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByUserLastName(lastName);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/full_name/{firstName}/{lastName}")
    public ModelAndView getUsersByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByFullName(firstName, lastName);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/passport_country/{passportCountry}")
    public ModelAndView getUsersByPassportIssueCountry(@RequestParam String passportCountry) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByPassportIssueCountry(passportCountry);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/residence_country/{residenceCountry}")
    public ModelAndView getUsersByResidenceCountry(@RequestParam String residenceCountry) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByResidenceCountry(residenceCountry);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/residence_city/{residenceCity}")
    public ModelAndView getUsersByResidenceCity(@RequestParam String residenceCity) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByResidenceCity(residenceCity);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    public ModelAndView getUserByOccupiedRoomNumber(@PathVariable Integer roomNumber) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto users = userService.getUserByOccupiedRoomNumber(roomNumber);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/edit_user/{id}")
    public ModelAndView showUserEditForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/users/user_edit");
        UserDto user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editUser(@PathVariable("id") long id,
                                 UserDto userDto,
                                 UserInDetailsDto userInDetailsDto,
                                 PassportDataDto passportDataDto,
                                 ContactDataDto contactDataDto,
                                 AddressDto addressDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users/get/" + id);
        userService.updateUser(id, userDto, userInDetailsDto, passportDataDto, contactDataDto, addressDto);
        return modelAndView;
    }

}
