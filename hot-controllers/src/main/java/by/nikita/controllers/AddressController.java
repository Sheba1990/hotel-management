package by.nikita.controllers;

import by.nikita.dto.AddressDto;
import by.nikita.services.api.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto addAddress(@RequestBody AddressDto addressDto) {
        return addressService.addAddress(addressDto);
    }

    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping(value = "/{id}")
    public AddressDto getAddressById(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping(value = "/user_first_name/{userFirstName}")
    public List<AddressDto> getAddressByUserFirstName(@PathVariable String userFirstName) {
        return addressService.getAddressByUserFirstName(userFirstName);
    }

    @GetMapping(value = "/user_last_name/{userLastName}")
    public List<AddressDto> getAddressByUserLastName(String userLastName) {
        return addressService.getAddressByUserLastName(userLastName);
    }

    @GetMapping(value = "/country/{country}")
    public List<AddressDto> getAddressByCountry(@PathVariable String country) {
        return addressService.getAddressByCountry(country);
    }

    @GetMapping(value = "/postal_code/{postalCode}")
    public List<AddressDto> getAddressByPostalCode(@PathVariable String postalCode) {
        return addressService.getAddressByPostalCode(postalCode);
    }

    @GetMapping(value = "/city/{city}")
    public List<AddressDto> getAddressByCity(String city) {
        return addressService.getAddressByCity(city);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateAddress(@PathVariable long id, @RequestBody AddressDto addressDto) {
        addressService.updateAddress(id, addressDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
