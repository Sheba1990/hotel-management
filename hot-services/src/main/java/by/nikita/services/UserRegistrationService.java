package by.nikita.services;

import by.nikita.dao.api.*;
import by.nikita.dto.UserDto;
import by.nikita.models.*;
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
    IPassportDataDao passportDataDao;

    @Autowired
    IContactDataDao contactDataDao;

    @Autowired
    IAddressDao addressDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        if (userDao.checkUsernamePresence(userDto.getUsername())) {
            throw new UsernameNotFoundException("User with the same username: " + userDto.getUsername() + " already exists!");
        }
        User user = new User();

        Address address = new Address();
        addressDao.create(address);

        PassportData passportData = new PassportData();
        passportDataDao.create(passportData);

        ContactData contactData = new ContactData();
        contactData.setAddress(address);
        contactDataDao.create(contactData);

        UserInDetails userInDetails = new UserInDetails();
        userInDetails.setContactData(contactData);
        userInDetails.setPassportData(passportData);
        userInDetailsDao.create(userInDetails);

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setActive(Boolean.FALSE);
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setUserInDetails(userInDetails);

        userDao.create(user);
    }

    @Override
    public void activateUser(String username) {
        User byUsername = userDao.getByUsername(username);
        byUsername.setActive(Boolean.TRUE);
        userDao.update(byUsername);
    }
}
