package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.dto.UserDto;
import by.nikita.models.User;
import by.nikita.models.UserDetails;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserDetailsDao userDetailsDao;


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByUsername(username);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        return UserDto.entityToDto(userDao.create(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.convertList(userDao.getAll());
    }

    @Override
    public UserDto getUserById(long id) {
        return UserDto.entityToDto(userDao.get(id));
    }

    @Override
    public List<UserDto> getUsersByFirstName(String firstName) {
        return UserDto.convertList(userDao.getUsersByFirstName(firstName));
    }

    @Override
    public List<UserDto> getUsersByLastName(String lastName) {
        return UserDto.convertList(userDao.getUsersByLastName(lastName));
    }

    @Override
    public List<UserDto> getUsersByFullName(String firstName, String lastName) {
        return UserDto.convertList(userDao.getUsersByFullName(firstName, lastName));
    }

    @Override
    public List<UserDto> getUsersByPassportIssueCountry(String passportCountry) {
        return UserDto.convertList(userDao.getUsersByPassportIssueCountry(passportCountry));
    }

    @Override
    public List<UserDto> getUsersByResidenceCountry(String residenceCountry) {
        return UserDto.convertList(userDao.getUsersByResidenceCountry(residenceCountry));
    }

    @Override
    public List<UserDto> getUsersByResidenceCity(String residenceCity) {
        return UserDto.convertList(userDao.getUsersByResidenceCountry(residenceCity));
    }

    @Override
    public List<UserDto> getUsersByOccupiedRoomNumber(Integer roomNumber) {
        return UserDto.convertList(userDao.getUsersByOccupiedRoomNumber(roomNumber));
    }

    @Override
    public void updateUser(long id, UserDto userDto) {
        User user = userDao.get(id);
        if (userDto.getUserName() != null && !StringUtils.isEmpty(userDto.getUserName())) {
            user.setUsername(userDto.getUserName());
        }
        if (userDto.getUserEmail() != null && !StringUtils.isEmpty(userDto.getUserEmail())) {
            user.setEmail(userDto.getUserEmail());
        }
        if (userDto.getUserPassword() != null && !StringUtils.isEmpty(userDto.getUserPassword())) {
            user.setPassword(userDto.getUserPassword());
        }
        userDao.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(userDao.get(id));
    }

    @Override
    public UserDto addUserDetailsToUser(long userDetailsId, long userId) {
        UserDetails userDetails = userDetailsDao.get(userDetailsId);
        User user = userDao.get(userId);
        user.setUserDetails(userDetails);
        userDao.update(user);
        return UserDto.entityToDto(user);
    }
}
