package by.nikita.services.api;

import by.nikita.dto.*;

import java.util.List;

public interface IUserService {

    UserDto getUserById(long id);

    List<UserDto> getAllUsers();

    List<UserDto> getUsersByUserFirstName(String firstName);

    List<UserDto> getUsersByUserLastName(String lastName);

    List<UserDto> getUsersByFullName(String firstName, String lastName);

    List<UserDto> getUsersByPassportIssueCountry(String passportCountry);

    List<UserDto> getUsersByResidenceCountry(String residenceCountry);

    List<UserDto> getUsersByResidenceCity(String residenceCity);

    UserDto getUserByOccupiedRoomNumber(Integer roomNumber);

    void updateUser(long id,
                    UserDto userDto,
                    UserInDetailsDto userInDetailsDto,
                    PassportDataDto passportDataDto,
                    ContactDataDto contactDataDto,
                    AddressDto addressDto);
}
