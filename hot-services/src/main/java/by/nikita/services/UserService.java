package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.dto.*;
import by.nikita.models.PassportData;
import by.nikita.models.User;
import by.nikita.models.UserDetails;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public List<UserDto> getUsersByOccupiedRoomNumber(Integer roomNumber) {
        return null;
    }

    @Override
    public void updateUser(long id, UserDto userDto, UserDetailsDto userDetailsDto, PassportDataDto passportDataDto, ContactDataDto contactDataDto, AddressDto addressDto) {
        User user = userDao.get(id);
        UserDetails userDetails = user.getUserDetails();
        PassportData passportData = user.getUserDetails().getPassportData();
        if (userDto.getUserName() != null && !StringUtils.isEmpty(userDto.getUserName())) {
            user.setUsername(userDto.getUserName());
        }
        if (userDto.getUserEmail() != null && !StringUtils.isEmpty(userDto.getUserEmail())) {
            user.setEmail(userDto.getUserEmail());
        }
        if (user.getUserDetails() != null) {
            if (userDto.getUserFirstName() != null && !StringUtils.isEmpty(userDto.getUserFirstName())) {
                userDetails.setFirstName(userDetailsDto.getUserFirstName());
            }
            if (userDto.getUserLastName() != null && !StringUtils.isEmpty(userDto.getUserLastName())) {
                userDetails.setLastName(userDetailsDto.getUserLastName());
            }
            if (userDto.getUserMiddleName() != null && !StringUtils.isEmpty(userDto.getUserMiddleName())) {
                userDetails.setMiddleName(userDetailsDto.getUserMiddleName());
            }
            if (userDto.getUserBirthDate() != null && !StringUtils.isEmpty(userDto.getUserBirthDate())) {
                userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
            }
        }
        if (user.getUserDetails().getPassportData() != null) {
            if (userDto.getUserPassportNumber() != null && !StringUtils.isEmpty(userDto.getUserPassportNumber())) {
                passportData.setPassportNumber(passportDataDto.getPassportNumber());
            }
            if (userDto.getUserPassportDateOfIssue() != null && !StringUtils.isEmpty(userDto.getUserPassportDateOfIssue())) {
                passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
            }
            if (userDto.getUserPassportDateOfExpiry() != null && !StringUtils.isEmpty(userDto.getUserPassportDateOfExpiry())) {
                passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
            }
            if (userDto.getUserPassportCountryOfIssue() != null && !StringUtils.isEmpty(userDto.getUserPassportCountryOfIssue())) {
                passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
            }
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
