package by.nikita.services;

import by.nikita.dao.api.IAddressDao;
import by.nikita.dao.api.IContactDataDao;
import by.nikita.dao.api.IPassportDataDao;
import by.nikita.dao.api.IUserInDetailsDao;
import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserInDetailsDto;
import by.nikita.models.Address;
import by.nikita.models.ContactData;
import by.nikita.models.PassportData;
import by.nikita.models.UserInDetails;
import by.nikita.services.api.IUserInDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserInDetailsService implements IUserInDetailsService {

    @Autowired
    IUserInDetailsDao userDetailsDao;

    @Autowired
    IContactDataDao contactDataDao;

    @Autowired
    IPassportDataDao passportDataDao;

    @Autowired
    IAddressDao addressDao;

    @Override
    public UserInDetailsDto addUserDetails(UserInDetailsDto userInDetailsDto,
                                           ContactDataDto contactDataDto,
                                           AddressDto addressDto,
                                           PassportDataDto passportDataDto) {

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

        UserInDetails userInDetails = new UserInDetails();
        userInDetails.setFirstName(userInDetailsDto.getUserFirstName());
        userInDetails.setMiddleName(userInDetailsDto.getUserMiddleName());
        userInDetails.setLastName(userInDetailsDto.getUserLastName());
        userInDetails.setGender(userInDetailsDto.getGender());
        userInDetails.setBirthDate(userInDetailsDto.getUserBirthDate());
        userInDetails.setPassportData(passportData);
        userInDetails.setContactData(contactData);

        UserInDetails newUserInDetails = userDetailsDao.create(userInDetails);

        return UserInDetailsDto.entityToDto(newUserInDetails);
    }

    @Override
    public UserInDetailsDto getUserDetailsByUserId(long userId) {
        return UserInDetailsDto.entityToDto(userDetailsDao.getUserDetailsByUserId(userId));
    }

    @Override
    public UserInDetailsDto getUserDetailsById(long id) {
        return UserInDetailsDto.entityToDto(userDetailsDao.get(id));
    }

    @Override
    public List<UserInDetailsDto> getAllUserDetails() {
        return UserInDetailsDto.convertList(userDetailsDao.getAll());
    }

    @Override
    public List<UserInDetailsDto> getUserDetailsByUserFirstName(String firstName) {
        return UserInDetailsDto.convertList(userDetailsDao.getUserDetailsByUserFirstName(firstName));
    }

    @Override
    public List<UserInDetailsDto> getUserDetailsByUserLastName(String lastName) {
        return UserInDetailsDto.convertList(userDetailsDao.getUserDetailsByUserLastName(lastName));
    }

    @Override
    public List<UserInDetailsDto> getUserDetailsByFullName(String firstName, String lastName) {
        return UserInDetailsDto.convertList(userDetailsDao.getUserDetailsByFullName(firstName, lastName));
    }

    @Override
    public List<UserInDetailsDto> getUsersByPassportIssueCountry(String passportCountry) {
        return UserInDetailsDto.convertList(userDetailsDao.getUsersByPassportIssueCountry(passportCountry));
    }

    @Override
    public List<UserInDetailsDto> getUsersByResidenceCountry(String residenceCountry) {
        return UserInDetailsDto.convertList(userDetailsDao.getUsersByResidenceCountry(residenceCountry));
    }

    @Override
    public List<UserInDetailsDto> getUsersByResidenceCity(String residenceCity) {
        return UserInDetailsDto.convertList(userDetailsDao.getUsersByResidenceCity(residenceCity));
    }

    @Override
    public UserInDetailsDto getUserByOccupiedRoomNumber(Integer roomNumber) {
        return UserInDetailsDto.entityToDto(userDetailsDao.getUserByOccupiedRoomNumber(roomNumber));
    }

    @Override
    public void updateUserDetails(long id,
                                  UserInDetailsDto userInDetailsDto,
                                  ContactDataDto contactDataDto,
                                  AddressDto addressDto,
                                  PassportDataDto passportDataDto) {

        UserInDetails userInDetails = userDetailsDao.get(id);
        PassportData passportData = userInDetails.getPassportData();
        ContactData contactData = userInDetails.getContactData();
        Address address = userInDetails.getContactData().getAddress();

        if (userInDetailsDto.getUserFirstName() != null && !StringUtils.isEmpty(userInDetailsDto.getUserFirstName())) {
            userInDetails.setFirstName(userInDetailsDto.getUserFirstName());
        }
        if (userInDetailsDto.getUserLastName() != null && !StringUtils.isEmpty(userInDetailsDto.getUserLastName())) {
            userInDetails.setLastName(userInDetailsDto.getUserLastName());
        }
        if (userInDetailsDto.getUserMiddleName() != null && !StringUtils.isEmpty(userInDetailsDto.getUserMiddleName())) {
            userInDetails.setMiddleName(userInDetails.getMiddleName());
        }
        if (userInDetailsDto.getGender() != null && !StringUtils.isEmpty(userInDetailsDto.getGender())) {
            userInDetails.setGender(userInDetailsDto.getGender());
        }
        if (userInDetailsDto.getUserBirthDate() != null && !StringUtils.isEmpty(userInDetailsDto.getUserBirthDate())) {
            userInDetails.setBirthDate(userInDetailsDto.getUserBirthDate());
        }

        if (userInDetails.getPassportData() != null) {
            if (userInDetailsDto.getUserPassportNumber() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPassportNumber())) {
                passportData.setPassportNumber(passportDataDto.getPassportNumber());
            }
            if (userInDetailsDto.getUserPassportCountryOfIssue() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPassportCountryOfIssue())) {
                passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
            }
            if (userInDetailsDto.getUserPassportDateOfIssue() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPassportDateOfIssue())) {
                passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
            }
            if (userInDetailsDto.getUserPassportDateOfExpiry() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPassportDateOfExpiry())) {
                passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
            }
            passportDataDao.update(passportData);
        }

        if (userInDetails.getContactData() != null) {
            if (userInDetailsDto.getUserPhoneNumber() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPhoneNumber())) {
                contactData.setPhoneNumber(contactDataDto.getUserPhoneNumber());
            }
            contactDataDao.update(contactData);
        }

        if (userInDetails.getContactData().getAddress() != null) {
            if (userInDetailsDto.getUserPostalCode() != null && !StringUtils.isEmpty(userInDetailsDto.getUserPostalCode())) {
                address.setPostalCode(addressDto.getPostalCode());
            }
            if (userInDetailsDto.getUserResidenceCountry() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceCountry())) {
                address.setCountry(addressDto.getCountry());
            }
            if (userInDetailsDto.getUserResidenceProvince() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceProvince())) {
                address.setProvince(addressDto.getProvince());
            }
            if (userInDetailsDto.getUserResidenceCity() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceCity())) {
                address.setCity(addressDto.getCity());
            }
            if (userInDetailsDto.getUserResidenceStreet() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceStreet())) {
                address.setStreet(addressDto.getStreet());
            }
            if (userInDetailsDto.getUserResidenceHomeNumber() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceHomeNumber())) {
                address.setHomeNumber(addressDto.getHomeNumber());
            }
            if (userInDetailsDto.getUserResidenceApartmentNumber() != null && !StringUtils.isEmpty(userInDetailsDto.getUserResidenceApartmentNumber())) {
                address.setApartmentNumber(addressDto.getApartmentNumber());
            }
            addressDao.update(address);
        }

        userDetailsDao.update(userInDetails);
    }

    @Override
    public void deleteUserDetails(long id) {
        userDetailsDao.delete(userDetailsDao.get(id));
    }

    @Override
    public UserInDetailsDto addContactDataToUserDetails(long contactDataId, long userDetailsId) {
        ContactData contactData = contactDataDao.get(contactDataId);
        UserInDetails userInDetails = userDetailsDao.get(userDetailsId);
        userInDetails.setContactData(contactData);
        userDetailsDao.update(userInDetails);
        return UserInDetailsDto.entityToDto(userInDetails);
    }

    @Override
    public UserInDetailsDto addPassportDataToUserDetails(long passportDataId, long userDetailsId) {
        PassportData passportData = passportDataDao.get(passportDataId);
        UserInDetails userInDetails = userDetailsDao.get(userDetailsId);
        userInDetails.setPassportData(passportData);
        userDetailsDao.update(userInDetails);
        return UserInDetailsDto.entityToDto(userInDetails);
    }
}
