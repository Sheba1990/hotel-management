package by.nikita.services.api;

import by.nikita.dto.*;

import java.util.List;

public interface IUserService {

    UserDto addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(long id);

    List<UserDto> getUsersByFirstName(String firstName);

    List<UserDto> getUsersByLastName(String lastName);

    List<UserDto> getUsersByFullName(String firstName, String lastName);

    List<UserDto> getUsersByPassportIssueCountry(String passportCountry);

    List<UserDto> getUsersByResidenceCountry(String residenceCountry);

    List<UserDto> getUsersByResidenceCity(String residenceCity);

    List<UserDto> getUsersByOccupiedRoomNumber(Integer roomNumber);

    void updateUser(long id, UserDto userDto);

    void deleteUser(long id);

    UserDto addUserDetailsToUser(long userId,
                                 UserDetailsDto userDetailsDto,
                                 ContactDataDto contactDataDto,
                                 AddressDto addressDto,
                                 PassportDataDto passportDataDto);

}
