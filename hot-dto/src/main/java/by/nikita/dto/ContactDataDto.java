package by.nikita.dto;

import by.nikita.models.ContactData;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ContactDataDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userFirstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userLastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userEmail;

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
}
