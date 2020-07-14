package by.nikita.models;

import javax.persistence.*;


@Entity
@Table(name = "contact_data_table")
public class ContactData extends IdAwareEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "contactData", fetch = FetchType.LAZY)
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public ContactData() {
    }

    public ContactData(String phoneNumber, UserDetails userDetails, Address address) {
        this.userDetails = userDetails;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

