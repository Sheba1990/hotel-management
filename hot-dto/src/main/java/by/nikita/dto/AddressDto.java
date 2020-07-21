package by.nikita.dto;

import by.nikita.models.Address;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class AddressDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String postalCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String country;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String province;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String city;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String street;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String homeNumber;

    private String userFirstName;

    private String userLastName;

    public static List<AddressDto> convertList(List<Address> addressList) {
        List<AddressDto> addresses = new ArrayList<>();
        for (Address address : addressList) {
            AddressDto addressDto = new AddressDto();
            if (address.getContactData().getUserDetails() != null) {
                addressDto.setUserFirstName(address.getContactData().getUserDetails().getFirstName());
                addressDto.setUserLastName(address.getContactData().getUserDetails().getLastName());
            } else {
                addressDto.setUserFirstName(null);
                addressDto.setUserLastName(null);
            }
            addressDto.setId(address.getId());
            addressDto.setCountry(address.getCountry());
            addressDto.setCity(address.getCity());
            addressDto.setStreet(address.getStreet());
            addressDto.setHomeNumber(address.getHomeNumber());
            addresses.add(addressDto);
        }
        return addresses;
    }

    public static AddressDto entityToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        if (address.getContactData().getUserDetails() != null) {
            addressDto.setUserFirstName(address.getContactData().getUserDetails().getFirstName());
            addressDto.setUserLastName(address.getContactData().getUserDetails().getLastName());
        } else {
            addressDto.setUserFirstName(null);
            addressDto.setUserLastName(null);
        }
        addressDto.setId(address.getId());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setCountry(address.getCountry());
        addressDto.setProvince(address.getProvince());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setHomeNumber(address.getHomeNumber());
        return addressDto;
    }

    public AddressDto() {
    }

    public AddressDto(Address address) {
        this.id = address.getId();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
        this.province = address.getProvince();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.homeNumber = address.getHomeNumber();
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
}