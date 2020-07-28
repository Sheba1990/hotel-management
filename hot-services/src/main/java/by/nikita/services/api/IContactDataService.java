package by.nikita.services.api;

import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.models.ContactData;

import java.util.List;

public interface IContactDataService {

    ContactDataDto addContactData(ContactDataDto contactDataDto);

    List<ContactDataDto> getAllContactData();

    ContactDataDto getContactDataById(long id);

    List<ContactDataDto> getContactDataByUserFirstName(String userFirstName);

    List<ContactDataDto> getContactDataByUserLastName(String userLastName);

    List<ContactDataDto> getContactDataByUserFullName(String userFirstName, String userLastName);

    void updateContactData(long id, ContactDataDto contactDataDto, AddressDto addressDto);

    void deleteContactData(long id);

    ContactDataDto addAddressToContactData(long addressId, long contactDataId);
}
