package by.nikita.services;

import by.nikita.dao.ContactDataDao;
import by.nikita.dao.PassportDataDao;
import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.dto.UserDetailsDto;
import by.nikita.models.ContactData;
import by.nikita.models.PassportData;
import by.nikita.models.UserDetails;
import by.nikita.services.api.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDetailsService implements IUserDetailsService {

    @Autowired
    IUserDetailsDao userDetailsDao;

    @Autowired
    ContactDataDao contactDataDao;

    @Autowired
    PassportDataDao passportDataDao;

    @Override
    public UserDetailsDto addUserDetails(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDetailsDto.getUserFirstName());
        userDetails.setMiddleName(userDetailsDto.getUserMiddleName());
        userDetails.setLastName(userDetailsDto.getUserLastName());
        userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
        return UserDetailsDto.entityToDto(userDetailsDao.create(userDetails));
    }

    @Override
    public UserDetailsDto getUserDetailsById(long id) {
        return UserDetailsDto.entityToDto(userDetailsDao.get(id));
    }

    @Override
    public List<UserDetailsDto> getAllUserDetails() {
        return UserDetailsDto.convertList(userDetailsDao.getAll());
    }

    @Override
    public List<UserDetailsDto> getUserDetailsByUserFirstName(String firstName) {
        return UserDetailsDto.convertList(userDetailsDao.getUserDetailsByUserFirstName(firstName));
    }

    @Override
    public List<UserDetailsDto> getUserDetailsByUserLastName(String lastName) {
        return UserDetailsDto.convertList(userDetailsDao.getUserDetailsByUserLastName(lastName));
    }

    @Override
    public void updateUserDetails(long id, UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userDetailsDao.get(id);
        if (userDetailsDto.getUserFirstName() != null && !StringUtils.isEmpty(userDetailsDto.getUserFirstName())) {
            userDetails.setFirstName(userDetailsDto.getUserFirstName());
        }
        if (userDetailsDto.getUserLastName() != null && !StringUtils.isEmpty(userDetailsDto.getUserLastName())) {
            userDetails.setLastName(userDetailsDto.getUserLastName());
        }
        if (userDetailsDto.getUserMiddleName() != null && !StringUtils.isEmpty(userDetailsDto.getUserMiddleName())) {
            userDetails.setMiddleName(userDetails.getMiddleName());
        }
        if (userDetailsDto.getUserBirthDate() != null && !StringUtils.isEmpty(userDetailsDto.getUserBirthDate())) {
            userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
        }
        userDetailsDao.update(userDetails);
    }

    @Override
    public void deleteUserDetails(long id) {
        userDetailsDao.delete(userDetailsDao.get(id));
    }

    @Override
    public UserDetailsDto addContactDataToUserDetails(long contactDataId, long userDetailsId) {
        ContactData contactData = contactDataDao.get(contactDataId);
        UserDetails userDetails = userDetailsDao.get(userDetailsId);
        userDetails.setContactData(contactData);
        userDetailsDao.update(userDetails);
        return UserDetailsDto.entityToDto(userDetails);
    }

    @Override
    public UserDetailsDto addPassportDataToUserDetails(long passportDataId, long userDetailsId) {
        PassportData passportData = passportDataDao.get(passportDataId);
        UserDetails userDetails = userDetailsDao.get(userDetailsId);
        userDetails.setPassportData(passportData);
        userDetailsDao.update(userDetails);
        return UserDetailsDto.entityToDto(userDetails);
    }
}
