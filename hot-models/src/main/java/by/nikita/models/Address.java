package by.nikita.models;

import javax.persistence.*;

@Entity
@Table(name = "address_table")
public class Address extends AbstractIdAwareEntity {

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home_number")
    private String homeNumber;

    private String apartmentNumber;

    @OneToOne(mappedBy = "address")
    private ContactData contactData;

    public Address() {
    }

    public Address(
            String postalCode,
            String country,
            String province,
            String city,
            String street,
            String homeNumber,
            String apartmentNumber,
            ContactData contactData) {
        this.postalCode = postalCode;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.apartmentNumber = apartmentNumber;
        this.contactData = contactData;
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

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }
}
