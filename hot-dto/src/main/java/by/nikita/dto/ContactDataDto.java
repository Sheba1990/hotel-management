package by.nikita.dto;

import by.nikita.models.ContactData;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDataDto extends AbstractIdAwareDto {

    private String userName;

    private String userFirstName;

    private String userLastName;

    private String userPhoneNumber;

    private String userEmail;

    private String postalCode;

    private String country;

    private String province;

    private String city;

    private String street;

    private String homeNumber;

    private String apartmentNumber;

    public static List<ContactDataDto> convertList(List<ContactData> contactDataList) {
        List<ContactDataDto> contacts = new ArrayList<>();
        for (ContactData contactData : contactDataList) {
            ContactDataDto contactDataDto = new ContactDataDto();
            contactDataDto.setId(contactData.getId());
            if (contactData.getUserDetails().getUser() != null) {
                contactDataDto.setUserName(contactData.getUserDetails().getUser().getUsername());
                contactDataDto.setUserEmail(contactData.getUserDetails().getUser().getEmail());
            } else {
                contactDataDto.setUserName(null);
                contactDataDto.setUserEmail(null);
            }
            contactDataDto.setUserPhoneNumber(contactData.getPhoneNumber());
            contacts.add(contactDataDto);
        }
        return contacts;
    }

    public static ContactDataDto entityToDto(ContactData contactData) {
        ContactDataDto contactDataDto = new ContactDataDto();
        contactDataDto.setId(contactData.getId());
        if (contactData.getUserDetails().getUser() != null) {
            contactDataDto.setUserName(contactData.getUserDetails().getUser().getUsername());
            contactDataDto.setUserEmail(contactData.getUserDetails().getUser().getEmail());
        } else {
            contactDataDto.setUserName(null);
            contactDataDto.setUserEmail(null);
        }
        if (contactData.getUserDetails() != null) {
            contactDataDto.setUserFirstName(contactData.getUserDetails().getFirstName());
            contactDataDto.setUserLastName(contactData.getUserDetails().getLastName());
        } else {
            contactDataDto.setUserFirstName(null);
            contactDataDto.setUserLastName(null);
        }
        contactDataDto.setUserPhoneNumber(contactData.getPhoneNumber());
        if (contactData.getAddress() != null) {
            contactDataDto.setPostalCode(contactData.getAddress().getPostalCode());
            contactDataDto.setCountry(contactData.getAddress().getCountry());
            contactDataDto.setProvince(contactData.getAddress().getProvince());
            contactDataDto.setCity(contactData.getAddress().getCity());
            contactDataDto.setStreet(contactData.getAddress().getStreet());
            contactDataDto.setHomeNumber(contactData.getAddress().getHomeNumber());
            contactDataDto.setApartmentNumber(contactData.getAddress().getApartmentNumber());
        }
        return contactDataDto;
    }

    public ContactDataDto() {
    }

    public ContactDataDto(ContactData contactData) {
        this.id = contactData.getId();
        this.userName = contactData.getUserDetails().getUser().getUsername();
        this.userFirstName = contactData.getUserDetails().getFirstName();
        this.userLastName = contactData.getUserDetails().getLastName();
        this.userPhoneNumber = contactData.getPhoneNumber();
        this.userEmail = contactData.getUserDetails().getUser().getEmail();
        this.postalCode = contactData.getAddress().getPostalCode();
        this.country = contactData.getAddress().getCountry();
        this.province = contactData.getAddress().getProvince();
        this.city = contactData.getAddress().getCity();
        this.street = contactData.getAddress().getStreet();
        this.homeNumber = contactData.getAddress().getHomeNumber();
        this.apartmentNumber = contactData.getAddress().getApartmentNumber();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
