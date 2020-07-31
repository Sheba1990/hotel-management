package by.nikita.services.api;

import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserDetailsDto;

import java.util.List;

public interface IUserDetailsService {

    UserDetailsDto addUserDetails(UserDetailsDto userDetailsDto, ContactDataDto contactDataDto, AddressDto addressDto, PassportDataDto passportDataDto);

    UserDetailsDto getUserDetailsById(long id);

    UserDetailsDto getUserDetailsByUserId(long userId);

    List<UserDetailsDto> getAllUserDetails();

    List<UserDetailsDto> getUserDetailsByUserFirstName(String firstName);

    List<UserDetailsDto> getUserDetailsByUserLastName(String lastName);

    void updateUserDetails(long id, UserDetailsDto userDetailsDto, ContactDataDto contactDataDto, AddressDto addressDto, PassportDataDto passportDataDto);

    void deleteUserDetails(long id);

    UserDetailsDto addContactDataToUserDetails(long contactDataId, long userDetailsId);

    UserDetailsDto addPassportDataToUserDetails(long passportDataId, long userDetailsId);
}
