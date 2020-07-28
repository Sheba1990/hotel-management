package by.nikita.services.api;

import by.nikita.dto.UserDetailsDto;

import java.util.List;

public interface IUserDetailsService {

    UserDetailsDto addUserDetails(UserDetailsDto userDetailsDto);

    UserDetailsDto getUserDetailsById(long id);

    List<UserDetailsDto> getAllUserDetails();

    List<UserDetailsDto> getUserDetailsByUserFirstName(String firstName);

    List<UserDetailsDto> getUserDetailsByUserLastName(String lastName);

    void updateUserDetails(long id, UserDetailsDto userDetailsDto);

    void deleteUserDetails(long id);

    UserDetailsDto addContactDataToUserDetails(long contactDataId, long userDetailsId);

    UserDetailsDto addPassportDataToUserDetails(long passportDataId, long userDetailsId);
}
