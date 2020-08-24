package by.nikita.services;

import by.nikita.dao.api.*;
import by.nikita.dto.*;
import by.nikita.models.*;
import by.nikita.models.enums.Role;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IPassportDataDao passportDataDao;

    @Autowired
    IUserDetailsDao userDetailsDao;

    @Autowired
    IAddressDao addressDao;

    @Autowired
    IContactDataDao contactDataDao;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        user.setRoles(Collections.singleton(Role.USER));
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
        return UserDto.convertList(userDao.getUsersByResidenceCity(residenceCity));
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
    public UserDto addUserDetailsToUser(long userId,
                                        UserDetailsDto userDetailsDto,
                                        ContactDataDto contactDataDto,
                                        AddressDto addressDto,
                                        PassportDataDto passportDataDto) {
        User user = userDao.get(userId);

        PassportData passportData = new PassportData();
        passportData.setPassportNumber(passportDataDto.getPassportNumber());
        passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
        passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
        passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
        passportDataDao.create(passportData);

        Address address = new Address();
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());
        address.setProvince(addressDto.getProvince());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setApartmentNumber(addressDto.getApartmentNumber());
        addressDao.create(address);

        ContactData contactData = new ContactData();
        contactData.setPhoneNumber(contactDataDto.getUserPhoneNumber());
        contactData.setAddress(address);
        contactDataDao.create(contactData);

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDetailsDto.getUserFirstName());
        userDetails.setMiddleName(userDetailsDto.getUserMiddleName());
        userDetails.setLastName(userDetailsDto.getUserLastName());
        userDetails.setGender(userDetailsDto.getGender());
        userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
        userDetails.setPassportData(passportData);
        userDetails.setContactData(contactData);
        userDetailsDao.create(userDetails);

        user.setUserDetails(userDetails);

        userDao.update(user);

        return UserDto.entityToDto(user);
    }
}
