package by.nikita.services;

import by.nikita.dao.api.IAddressDao;
import by.nikita.dao.api.IContactDataDao;
import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.models.Address;
import by.nikita.models.ContactData;
import by.nikita.services.api.IContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContactDataService implements IContactDataService {

    @Autowired
    IContactDataDao contactDataDao;

    @Autowired
    IAddressDao addressDao;

    @Override
    public ContactDataDto addContactData(ContactDataDto contactDataDto) {
        ContactData contactData = new ContactData();
        contactData.setPhoneNumber(contactDataDto.getUserPhoneNumber());
        return ContactDataDto.entityToDto(contactDataDao.create(contactData));
    }

    @Override
    public List<ContactDataDto> getAllContactData() {
        return ContactDataDto.convertList(contactDataDao.getAll());
    }

    @Override
    public ContactDataDto getContactDataById(long id) {
        return ContactDataDto.entityToDto(contactDataDao.get(id));
    }

    @Override
    public List<ContactDataDto> getContactDataByUserFirstName(String userFirstName) {
        return ContactDataDto.convertList(contactDataDao.getContactDataByUserFirstName(userFirstName));
    }

    @Override
    public List<ContactDataDto> getContactDataByUserLastName(String userLastName) {
        return ContactDataDto.convertList(contactDataDao.getContactDataByUserLastName(userLastName));
    }

    @Override
    public List<ContactDataDto> getContactDataByUserFullName(String userFirstName, String userLastName) {
        return ContactDataDto.convertList(contactDataDao.getContactDataByUserFullName(userFirstName, userLastName));
    }

    @Override
    public void updateContactData(long id, ContactDataDto contactDataDto, AddressDto addressDto) {
        ContactData contactData = contactDataDao.get(id);
        Address address = contactData.getAddress();
        if (contactDataDto.getUserPhoneNumber() != null && !StringUtils.isEmpty(contactDataDto.getUserPhoneNumber())) {
            contactData.setPhoneNumber(contactDataDto.getUserPhoneNumber());
        }
        if (contactData.getAddress() != null) {
            if (contactDataDto.getPostalCode() != null && !StringUtils.isEmpty(contactDataDto.getPostalCode())) {
                address.setPostalCode(addressDto.getPostalCode());
            }
            if (contactDataDto.getCountry() != null && !StringUtils.isEmpty(contactDataDto.getCountry())) {
                address.setCountry(addressDto.getCountry());
            }
            if (contactDataDto.getProvince() != null && !StringUtils.isEmpty(contactDataDto.getProvince())) {
                address.setProvince(addressDto.getProvince());
            }
            if (contactDataDto.getCity() != null && !StringUtils.isEmpty(contactDataDto.getCity())) {
                address.setCity(addressDto.getCity());
            }
            if (contactDataDto.getStreet() != null && !StringUtils.isEmpty(contactDataDto.getStreet())) {
                address.setStreet(addressDto.getStreet());
            }
            if (contactDataDto.getHomeNumber() != null && !StringUtils.isEmpty(contactDataDto.getHomeNumber())) {
                address.setHomeNumber(addressDto.getHomeNumber());
            }
            if (contactDataDto.getApartmentNumber() != null && !StringUtils.isEmpty(contactDataDto.getApartmentNumber())) {
                address.setApartmentNumber(addressDto.getApartmentNumber());
            }
        }
        contactDataDao.update(contactData);
    }

    @Override
    public void deleteContactData(long id) {
        contactDataDao.delete(contactDataDao.get(id));
    }

    @Override
    public ContactDataDto addAddressToContactData(long addressId, long contactDataId) {
        Address address = addressDao.get(addressId);
        ContactData contactData = contactDataDao.get(contactDataId);
        contactData.setAddress(address);
        contactDataDao.update(contactData);
        return ContactDataDto.entityToDto(contactData);
    }
}
