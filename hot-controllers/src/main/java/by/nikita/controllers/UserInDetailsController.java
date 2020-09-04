package by.nikita.controllers;

import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserInDetailsDto;
import by.nikita.services.api.IUserInDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_details")
public class UserInDetailsController {

    @Autowired
    IUserInDetailsService userDetailsService;

    @PostMapping("/")
    UserInDetailsDto addUserDetails(@RequestBody UserInDetailsDto userInDetailsDto,
                                    @RequestBody ContactDataDto contactDataDto,
                                    @RequestBody AddressDto addressDto,
                                    @RequestBody PassportDataDto passportDataDto) {
        return userDetailsService.addUserDetails(userInDetailsDto, contactDataDto, addressDto, passportDataDto);
    }

    @GetMapping("/{id}")
    UserInDetailsDto getUserDetailsById(@PathVariable long id) {
        return userDetailsService.getUserDetailsById(id);
    }

    @GetMapping("/userId/{userId}")
    UserInDetailsDto getUserDetailsByUserId(@PathVariable long userId) {
        return userDetailsService.getUserDetailsByUserId(userId);
    }

    @GetMapping
    List<UserInDetailsDto> getAllUserDetails() {
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping("/user_first_name/{firstName}")
    List<UserInDetailsDto> getUserDetailsByUserFirstName(@PathVariable String firstName) {
        return userDetailsService.getUserDetailsByUserFirstName(firstName);
    }

    @GetMapping("/user_last_name/{lastName}")
    List<UserInDetailsDto> getUserDetailsByUserLastName(@PathVariable String lastName) {
        return userDetailsService.getUserDetailsByUserLastName(lastName);
    }

    @PutMapping("/{id}")
    void updateUserDetails(@PathVariable long id,
                           @RequestBody UserInDetailsDto userInDetailsDto,
                           @RequestBody ContactDataDto contactDataDto,
                           @RequestBody AddressDto addressDto,
                           @RequestBody PassportDataDto passportDataDto) {
        userDetailsService.updateUserDetails(id, userInDetailsDto, contactDataDto, addressDto, passportDataDto);
    }

    @DeleteMapping("/{id}")
    void deleteUserDetails(long id) {
        userDetailsService.deleteUserDetails(id);

    }

    @PutMapping("/add_contacts_to_details/{contactDataId}/{userDetailsId}")
    UserInDetailsDto addContactDataToUserDetails(@PathVariable long contactDataId,
                                                 @PathVariable long userDetailsId) {
        return userDetailsService.addContactDataToUserDetails(contactDataId, userDetailsId);
    }

    @PutMapping("/add_passport_to_details/{contactDataId}/{userDetailsId}")
    UserInDetailsDto addPassportDataToUserDetails(@PathVariable long passportDataId,
                                                  @PathVariable long userDetailsId) {
        return userDetailsService.addPassportDataToUserDetails(passportDataId, userDetailsId);
    }

}
