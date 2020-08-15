package by.nikita.controllers;

import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserDetailsDto;
import by.nikita.services.api.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_details")
public class UserDetailsController {

    @Autowired
    IUserDetailsService userDetailsService;

    @PostMapping("/")
    UserDetailsDto addUserDetails(@RequestBody UserDetailsDto userDetailsDto,
                                  @RequestBody ContactDataDto contactDataDto,
                                  @RequestBody AddressDto addressDto,
                                  @RequestBody PassportDataDto passportDataDto) {
        return userDetailsService.addUserDetails(userDetailsDto, contactDataDto, addressDto, passportDataDto);
    }

    @GetMapping("/{id}")
    UserDetailsDto getUserDetailsById(@PathVariable long id) {
        return userDetailsService.getUserDetailsById(id);
    }

    @GetMapping("/userId/{userId}")
    UserDetailsDto getUserDetailsByUserId(@PathVariable long userId) {
        return userDetailsService.getUserDetailsByUserId(userId);
    }

    @GetMapping
    List<UserDetailsDto> getAllUserDetails() {
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping("/user_first_name/{firstName}")
    List<UserDetailsDto> getUserDetailsByUserFirstName(@PathVariable String firstName) {
        return userDetailsService.getUserDetailsByUserFirstName(firstName);
    }

    @GetMapping("/user_last_name/{lastName}")
    List<UserDetailsDto> getUserDetailsByUserLastName(@PathVariable String lastName) {
        return userDetailsService.getUserDetailsByUserLastName(lastName);
    }

    @PutMapping("/{id}")
    void updateUserDetails(@PathVariable long id,
                           @RequestBody UserDetailsDto userDetailsDto,
                           @RequestBody ContactDataDto contactDataDto,
                           @RequestBody AddressDto addressDto,
                           @RequestBody PassportDataDto passportDataDto) {
        userDetailsService.updateUserDetails(id, userDetailsDto, contactDataDto, addressDto, passportDataDto);
    }

    @DeleteMapping("/{id}")
    void deleteUserDetails(long id) {
        userDetailsService.deleteUserDetails(id);

    }

    @PutMapping("/add_contacts_to_details/{contactDataId}/{userDetailsId}")
    UserDetailsDto addContactDataToUserDetails(@PathVariable long contactDataId,
                                               @PathVariable long userDetailsId) {
        return userDetailsService.addContactDataToUserDetails(contactDataId, userDetailsId);
    }

    @PutMapping("/add_passport_to_details/{contactDataId}/{userDetailsId}")
    UserDetailsDto addPassportDataToUserDetails(@PathVariable long passportDataId,
                                                @PathVariable long userDetailsId) {
        return userDetailsService.addPassportDataToUserDetails(passportDataId, userDetailsId);
    }

}
