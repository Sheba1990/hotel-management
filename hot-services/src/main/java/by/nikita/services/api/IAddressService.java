package by.nikita.services.api;

import by.nikita.dto.AddressDto;

import java.util.List;

public interface IAddressService {

    AddressDto addAddress(AddressDto addressDto);

    List<AddressDto> getAllAddresses();

    AddressDto getAddressById(long id);

    List<AddressDto> getAddressByUserFirstName(String userFirstName);

    List<AddressDto> getAddressByUserLastName(String userLastName);

    List<AddressDto> getAddressByCountry(String country);

    List<AddressDto> getAddressByPostalCode(String postalCode);

    List<AddressDto> getAddressByCity(String city);

    void updateAddress(long id, AddressDto addressDto);

    void deleteAddress(long id);
}
