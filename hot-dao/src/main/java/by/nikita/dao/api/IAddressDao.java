package by.nikita.dao.api;

import by.nikita.models.Address;

import java.util.List;

public interface IAddressDao extends IAbstractGenericDao<Address> {

    List<Address> getAddressByUserFirstName(String userFirstName);

    List<Address> getAddressByUserLastName(String userLastName);

    List<Address> getAddressByCountry(String country);

    List<Address> getAddressByPostalCode(String postalCode);

    List<Address> getAddressByCity(String city);
}
