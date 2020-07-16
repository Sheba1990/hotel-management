package by.nikita.dao.api;

import by.nikita.models.Address;

import java.util.List;

public interface IAddressDao extends IAbstractGenericDao<Address> {

    List<Address> getAddressByCountry(String country);

    List<Address> getAddressByPostalCode(String postalCode);

    List<Address> getAddressByCity(String city);
}
