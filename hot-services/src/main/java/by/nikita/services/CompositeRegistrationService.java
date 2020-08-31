package by.nikita.services;

import by.nikita.dto.UserDto;
import by.nikita.services.api.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CompositeRegistrationService implements IRegistrationService {

    @Autowired
    private List<IRegistrationService> registrationServices;

    @Override
    public void registerUser(UserDto userDto) {
        registrationServices.forEach(s -> s.registerUser(userDto));
    }

    @Override
    public void activateUser(String username) {
        registrationServices.forEach(s -> s.activateUser(username));
    }
}
