package by.nikita.services.api;

import by.nikita.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    UserDto getUserById(long id);

    List<UserDto> searchAll(String keyword);

    List<UserDto> getAllUsers();

    UserDto getUserByUsername(String username);

    List<UserDto> getUsersByUserFirstName(String firstName);

    List<UserDto> getUsersByUserLastName(String lastName);

    List<UserDto> getUsersByFullName(String firstName, String lastName, String middleName);

    List<UserDto> getUsersByPassportIssueCountry(String passportCountry);

    List<UserDto> getUsersByResidenceCountry(String residenceCountry);

    List<UserDto> getUsersByResidenceCity(String residenceCity);

    UserDto getUserByOccupiedRoomNumber(Integer roomNumber);

    void updateUser(String username,
                    UserDto userDto,
                    UserInDetailsDto userInDetailsDto,
                    PassportDataDto passportDataDto,
                    ContactDataDto contactDataDto,
                    AddressDto addressDto,
                    MultipartFile file) throws IOException;

    void deleteUser(long id);
}
