package by.nikita.services;

import by.nikita.dao.api.IAddressDao;
import by.nikita.dao.api.IContactDataDao;
import by.nikita.dao.api.IPassportDataDao;
import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserDetailsDto;
import by.nikita.models.Address;
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
    IContactDataDao contactDataDao;

    @Autowired
    IPassportDataDao passportDataDao;

    @Autowired
    IAddressDao addressDao;

    @Override
    public UserDetailsDto addUserDetails(UserDetailsDto userDetailsDto,
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

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDetailsDto.getUserFirstName());
        userDetails.setMiddleName(userDetailsDto.getUserMiddleName());
        userDetails.setLastName(userDetailsDto.getUserLastName());
        userDetails.setGender(userDetailsDto.getGender());
        userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
        userDetails.setPassportData(passportData);
        userDetails.setContactData(contactData);

        UserDetails newUserDetails = userDetailsDao.create(userDetails);

        return UserDetailsDto.entityToDto(newUserDetails);
    }

    @Override
    public UserDetailsDto getUserDetailsByUserId(long userId) {
        return UserDetailsDto.entityToDto(userDetailsDao.getUserDetailsByUserId(userId));
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
    public void updateUserDetails(long id,
                                  UserDetailsDto userDetailsDto,
                                  ContactDataDto contactDataDto,
                                  AddressDto addressDto,
                                  PassportDataDto passportDataDto) {

        UserDetails userDetails = userDetailsDao.get(id);
        PassportData passportData = userDetails.getPassportData();
        ContactData contactData = userDetails.getContactData();
        Address address = userDetails.getContactData().getAddress();

        if (userDetailsDto.getUserFirstName() != null && !StringUtils.isEmpty(userDetailsDto.getUserFirstName())) {
            userDetails.setFirstName(userDetailsDto.getUserFirstName());
        }
        if (userDetailsDto.getUserLastName() != null && !StringUtils.isEmpty(userDetailsDto.getUserLastName())) {
            userDetails.setLastName(userDetailsDto.getUserLastName());
        }
        if (userDetailsDto.getUserMiddleName() != null && !StringUtils.isEmpty(userDetailsDto.getUserMiddleName())) {
            userDetails.setMiddleName(userDetails.getMiddleName());
        }
        if (userDetailsDto.getGender() != null && !StringUtils.isEmpty(userDetailsDto.getGender())) {
            userDetails.setGender(userDetailsDto.getGender());
        }
        if (userDetailsDto.getUserBirthDate() != null && !StringUtils.isEmpty(userDetailsDto.getUserBirthDate())) {
            userDetails.setBirthDate(userDetailsDto.getUserBirthDate());
        }

        if (userDetails.getPassportData() != null) {
            if (userDetailsDto.getUserPassportNumber() != null && !StringUtils.isEmpty(userDetailsDto.getUserPassportNumber())) {
                passportData.setPassportNumber(passportDataDto.getPassportNumber());
            }
            if (userDetailsDto.getUserPassportCountryOfIssue() != null && !StringUtils.isEmpty(userDetailsDto.getUserPassportCountryOfIssue())) {
                passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
            }
            if (userDetailsDto.getUserPassportDateOfIssue() != null && !StringUtils.isEmpty(userDetailsDto.getUserPassportDateOfIssue())) {
                passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
            }
            if (userDetailsDto.getUserPassportDateOfExpiry() != null && !StringUtils.isEmpty(userDetailsDto.getUserPassportDateOfExpiry())) {
                passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
            }
            passportDataDao.update(passportData);
        }

        if (userDetails.getContactData() != null) {
            if (userDetailsDto.getUserPhoneNumber() != null && !StringUtils.isEmpty(userDetailsDto.getUserPhoneNumber())) {
                contactData.setPhoneNumber(contactDataDto.getUserPhoneNumber());
            }
            contactDataDao.update(contactData);
        }

        if (userDetails.getContactData().getAddress() != null) {
            if (userDetailsDto.getUserPostalCode() != null && !StringUtils.isEmpty(userDetailsDto.getUserPostalCode())) {
                address.setPostalCode(addressDto.getPostalCode());
            }
            if (userDetailsDto.getUserResidenceCountry() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceCountry())) {
                address.setCountry(addressDto.getCountry());
            }
            if (userDetailsDto.getUserResidenceProvince() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceProvince())) {
                address.setProvince(addressDto.getProvince());
            }
            if (userDetailsDto.getUserResidenceCity() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceCity())) {
                address.setCity(addressDto.getCity());
            }
            if (userDetailsDto.getUserResidenceStreet() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceStreet())) {
                address.setStreet(addressDto.getStreet());
            }
            if (userDetailsDto.getUserResidenceHomeNumber() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceHomeNumber())) {
                address.setHomeNumber(addressDto.getHomeNumber());
            }
            if (userDetailsDto.getUserResidenceApartmentNumber() != null && !StringUtils.isEmpty(userDetailsDto.getUserResidenceApartmentNumber())) {
                address.setApartmentNumber(addressDto.getApartmentNumber());
            }
            addressDao.update(address);
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
