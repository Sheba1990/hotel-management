package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dto.UserDto;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

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
