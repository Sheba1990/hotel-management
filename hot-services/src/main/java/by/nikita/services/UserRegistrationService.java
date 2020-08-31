package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dto.UserDto;
import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.models.UserInDetails;
import by.nikita.services.api.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserRegistrationService implements IRegistrationService {

    @Autowired
    IUserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        if (userDao.checkUsernamePresence(userDto.getUserName())) {
            throw new UsernameNotFoundException("User is presented");
        }
        User user = new User();
        UserInDetails userInDetails = new UserInDetails();
        user.setUsername(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getUserPassword()));
        user.setEmail(userDto.getUserEmail());
        user.setActive(Boolean.FALSE);
        user.setRoles(Collections.singleton(Role.USER));
        user.setUserInDetails(userInDetails);
        userDao.create(user);
    }

    @Override
    public void activateUser(String username) {
        User user = userDao.getByUsername(username);
        user.setActive(Boolean.TRUE);
        userDao.update(user);
    }
}
