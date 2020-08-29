package by.nikita.services.api;

import by.nikita.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {

    void addUser(UserDto userDto);

    UserDetails getUserByUsername(String username);

    UserDto getUserById(long id);

    List<UserDto> getAllUsers();


}
