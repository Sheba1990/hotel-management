package by.nikita.services.api;

import by.nikita.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

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

    UserDto addUserDetailsToUser(long userDetailsId, long userId);

}
