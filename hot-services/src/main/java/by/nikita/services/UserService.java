package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dao.api.IUserInDetailsDao;
import by.nikita.dto.UserDto;
import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.models.UserInDetails;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserInDetailsDao userDetailsDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDto userDto) {

        User user = new User();
        UserInDetails userInDetails = new UserInDetails();
        userDetailsDao.create(userInDetails);

        user.setPassword(passwordEncoder.encode(userDto.getUserPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setUserInDetails(userInDetails);
        userDao.create(user);
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public UserDto getUserById(long id) {
        return UserDto.entityToDto(userDao.get(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.convertList(userDao.getAll());
    }
}
