package by.nikita.services.api;

import by.nikita.dto.UserDto;

public interface IRegistrationService {

    void registerUser(UserDto userDto);

    void activateUser(String username);
}
