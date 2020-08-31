package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dao.api.IUserInDetailsDao;
import by.nikita.dto.UserDto;
import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.services.api.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserRegistrationService implements IRegistrationService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserInDetailsDao userInDetailsDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        if (userDao.checkUsernamePresence(userDto.getUsername())) {
            throw new UsernameNotFoundException("User is presented");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setActive(Boolean.FALSE);
        user.setRoles(Collections.singleton(Role.USER));
        userDao.create(user);
    }

    @Override
    public void activateUser(String username) {
        User byUsername = userDao.getByUsername(username);
        byUsername.setActive(Boolean.TRUE);
        userDao.update(byUsername);
    }
}
