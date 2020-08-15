package by.nikita.controllers;

import by.nikita.dto.*;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto addUser(UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    UserDto getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/first_name/{firstName}")
    List<UserDto> getUsersByFirstName(String firstName) {
        return userService.getUsersByFirstName(firstName);
    }

    @GetMapping(value = "/last_name/{lastName}")
    List<UserDto> getUsersByLastName(String lastName) {
        return userService.getUsersByLastName(lastName);
    }

    @GetMapping(value = "/full_name/{firstName}/{lastName}")
    List<UserDto> getUsersByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return userService.getUsersByFullName(firstName, lastName);
    }

    @GetMapping(value = "/passport_country/{passportCountry}")
    List<UserDto> getUsersByPassportIssueCountry(@PathVariable String passportCountry) {
        return userService.getUsersByPassportIssueCountry(passportCountry);
    }

    @GetMapping(value = "/residence_country/{residenceCountry}")
    List<UserDto> getUsersByResidenceCountry(@PathVariable String residenceCountry) {
        return userService.getUsersByResidenceCountry(residenceCountry);
    }

    @GetMapping(value = "/residence_city/{residenceCity}")
    List<UserDto> getUsersByResidenceCity(@PathVariable String residenceCity) {
        return userService.getUsersByResidenceCity(residenceCity);
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    List<UserDto> getUsersByOccupiedRoomNumber(@PathVariable Integer roomNumber) {
        return userService.getUsersByOccupiedRoomNumber(roomNumber);
    }

    @PutMapping(value = "/edit/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);

    }

    @DeleteMapping(value = "/{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    UserDto addUserDetailsToUser(long userId,
                                 UserDetailsDto userDetailsDto,
                                 ContactDataDto contactDataDto,
                                 AddressDto addressDto,
                                 PassportDataDto passportDataDto) {
        return userService.addUserDetailsToUser(userId, userDetailsDto, contactDataDto, addressDto, passportDataDto);
    }

}
