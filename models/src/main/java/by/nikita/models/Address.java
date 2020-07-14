package by.nikita.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "address_table")
public class Address extends IdAwareEntity {

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

    @OneToMany(mappedBy = "address")
    private List<ContactData> contactDataList;

    public Address() {
    }

    public Address(String postalCode, String country, String province, String city, String street, String homeNumber, List<ContactData> contactDataList) {
        this.postalCode = postalCode;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.contactDataList = contactDataList;
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

    public List<ContactData> getContactDataList() {
        return contactDataList;
    }

    public void setContactDataList(List<ContactData> contactDataList) {
        this.contactDataList = contactDataList;
    }
}
