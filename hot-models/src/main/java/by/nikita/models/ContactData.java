package by.nikita.models;

import javax.persistence.*;


@Entity
@Table(name = "contact_data_table")
public class ContactData extends AbstractIdAwareEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "contactData", fetch = FetchType.LAZY)
    private UserInDetails userInDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public ContactData() {
    }

    public ContactData(String phoneNumber, UserInDetails userInDetails, Address address) {
        this.userInDetails = userInDetails;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserInDetails getUserInDetails() {
        return userInDetails;
    }

    public void setUserInDetails(UserInDetails userInDetails) {
        this.userInDetails = userInDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

