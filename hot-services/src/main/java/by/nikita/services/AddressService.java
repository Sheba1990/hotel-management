package by.nikita.services;

import by.nikita.dao.api.IAddressDao;
import by.nikita.dto.AddressDto;
import by.nikita.models.Address;
import by.nikita.services.api.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    IAddressDao addressDao;

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());
        address.setProvince(addressDto.getProvince());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setApartmentNumber(addressDto.getApartmentNumber());
        return AddressDto.entityToDto(addressDao.create(address));
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return AddressDto.convertList(addressDao.getAll());
    }

    @Override
    public AddressDto getAddressById(long id) {
        return AddressDto.entityToDto(addressDao.get(id));
    }

    @Override
    public List<AddressDto> getAddressByUserFirstName(String userFirstName) {
        return AddressDto.convertList(addressDao.getAddressByUserFirstName(userFirstName));
    }

    @Override
    public List<AddressDto> getAddressByUserLastName(String userLastName) {
        return AddressDto.convertList(addressDao.getAddressByUserLastName(userLastName));
    }

    @Override
    public List<AddressDto> getAddressByCountry(String country) {
        return AddressDto.convertList(addressDao.getAddressByCountry(country));
    }

    @Override
    public List<AddressDto> getAddressByPostalCode(String postalCode) {
        return AddressDto.convertList(addressDao.getAddressByPostalCode(postalCode));
    }

    @Override
    public List<AddressDto> getAddressByCity(String city) {
        return AddressDto.convertList(addressDao.getAddressByCity(city));
    }

    @Override
    public void updateAddress(long id, AddressDto addressDto) {
        Address address = addressDao.get(id);
        if (addressDto.getPostalCode() != null && !StringUtils.isEmpty(addressDto.getPostalCode())) {
            address.setPostalCode(addressDto.getPostalCode());
        }
        if (addressDto.getCountry() != null && !StringUtils.isEmpty(addressDto.getCountry())) {
            address.setCountry(addressDto.getCountry());
        }
        if (addressDto.getProvince() != null && !StringUtils.isEmpty(addressDto.getProvince())) {
            address.setProvince(addressDto.getProvince());
        }
        if (addressDto.getCity() != null && !StringUtils.isEmpty(addressDto.getCity())) {
            address.setCity(addressDto.getCity());
        }
        if (addressDto.getStreet() != null && !StringUtils.isEmpty(addressDto.getStreet())) {
            address.setStreet(addressDto.getStreet());
        }
        if (addressDto.getHomeNumber() != null && !StringUtils.isEmpty(addressDto.getHomeNumber())) {
            address.setHomeNumber(addressDto.getHomeNumber());
        }
        if (addressDto.getApartmentNumber() != null && !StringUtils.isEmpty(addressDto.getApartmentNumber())) {
            address.setApartmentNumber(addressDto.getApartmentNumber());
        }
        addressDao.update(address);
    }

    @Override
    public void deleteAddress(long id) {
        addressDao.delete(addressDao.get(id));
    }
}
