package by.nikita.services;

import by.nikita.dao.api.*;
import by.nikita.dto.*;
import by.nikita.models.*;
import by.nikita.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService implements IUserService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserInDetailsDao userInDetailsDao;

    @Autowired
    private IPassportDataDao passportDataDao;

    @Autowired
    private IContactDataDao contactDataDao;

    @Autowired
    private IAddressDao addressDao;


    @Override
    public UserDto getUserById(long id) {
        return UserDto.entityToDto(userDao.get(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.convertList(userDao.getAll());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return UserDto.entityToDto(userDao.getByUsername(username));
    }

    @Override
    public List<UserDto> getUsersByUserFirstName(String firstName) {
        return UserDto.convertList(userDao.getUsersByUserFirstName(firstName));
    }

    @Override
    public List<UserDto> getUsersByUserLastName(String lastName) {
        return UserDto.convertList(userDao.getUsersByUserLastName(lastName));
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
    public UserDto getUserByOccupiedRoomNumber(Integer roomNumber) {
        return UserDto.entityToDto(userDao.getUserByOccupiedRoomNumber(roomNumber));
    }

    @Override
    public void updateUser(String username,
                           UserDto userDto,
                           UserInDetailsDto userInDetailsDto,
                           PassportDataDto passportDataDto,
                           ContactDataDto contactDataDto,
                           AddressDto addressDto,
                           MultipartFile file) throws IOException {

        User user = userDao.getByUsername(username);
        UserInDetails userInDetails = user.getUserInDetails();
        PassportData passportData = user.getUserInDetails().getPassportData();
        ContactData contactData = user.getUserInDetails().getContactData();
        Address address = user.getUserInDetails().getContactData().getAddress();

        if (user.getUserInDetails().getContactData().getAddress() != null) {
            address.setPostalCode(userDto.getUserPostalCode());
            address.setCountry(userDto.getUserResidenceCountry());
            address.setProvince(userDto.getUserResidenceProvince());
            address.setCity(userDto.getUserResidenceCity());
            address.setStreet(userDto.getUserResidenceStreet());
            address.setHomeNumber(userDto.getUserResidenceHomeNumber());
            address.setApartmentNumber(userDto.getUserResidenceApartmentNumber());
            addressDao.update(address);
        }
        if (user.getUserInDetails().getContactData() != null) {
            contactData.setPhoneNumber(userDto.getUserPhoneNumber());
            contactDataDao.update(contactData);
        }
        if (user.getUserInDetails().getPassportData() != null) {
            passportData.setPassportNumber(userDto.getUserPassportNumber());
            passportData.setCountryOfIssue(userDto.getUserPassportCountryOfIssue());
            passportData.setDateOfIssue(userDto.getUserPassportDateOfIssue());
            passportData.setDateOfExpiry(userDto.getUserPassportDateOfExpiry());
            passportDataDao.update(passportData);
        }
        if (user.getUserInDetails() != null) {
            userInDetails.setFirstName(userDto.getUserFirstName());
            userInDetails.setMiddleName(userDto.getUserMiddleName());
            userInDetails.setLastName(userDto.getUserLastName());
            userInDetails.setBirthDate(userDto.getUserBirthDate());
            userInDetails.setGender(userDto.getGender());
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();

                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));

                userInDetails.setFileName(resultFileName);
            }
            userInDetailsDao.update(userInDetails);
        }
        userDao.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(userDao.get(id));
    }
}
