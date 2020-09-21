package by.nikita.controllers;

import by.nikita.dao.api.IUserInDetailsDao;
import by.nikita.dto.*;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${user.upload.path}")
    private String userUploadPath;

    @Autowired
    private IUserService userService;

    @Autowired
    IUserInDetailsDao userInDetailsDao;

    @GetMapping("/get/{id}")
    public ModelAndView getUserById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = userService.getUserById(id);
        modelAndView.setViewName("/views/users/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getAllUsers();
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/")
    public ModelAndView searchUsers(@RequestParam("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.searchAll(keyword);
        modelAndView.setViewName("/views/users/all_users");
        modelAndView.addObject("users", users);
        modelAndView.addObject("keyword", keyword);
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
    public ModelAndView getUsersByFullName(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String middleName) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getUsersByFullName(firstName, lastName, middleName);
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

    @GetMapping(value = "/username/")
    public ModelAndView getUserByUsername(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        UserDto user = userService.getUserByUsername(username);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/views/users/user");
        return modelAndView;
    }

    @GetMapping(value = "/edit/")
    public ModelAndView showEditFormForUser(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        UserDto user = userService.getUserByUsername(username);
        modelAndView.setViewName("/views/users/user_edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/edit_user/{id}")
    public ModelAndView showEditFormForAdmin(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = userService.getUserById(id);
        modelAndView.setViewName("/views/users/user_edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/save/")
    public ModelAndView editUser(@RequestParam("username") String username,
                                 UserDto userDto,
                                 UserInDetailsDto userInDetailsDto,
                                 PassportDataDto passportDataDto,
                                 ContactDataDto contactDataDto,
                                 AddressDto addressDto,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateUser(username, userDto, userInDetailsDto, passportDataDto, contactDataDto, addressDto, file);
        modelAndView.addObject("username", username);
        modelAndView.setViewName("redirect:/users/username/?username={username}");
        return modelAndView;
    }

    @PostMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteUser(id);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }
}
