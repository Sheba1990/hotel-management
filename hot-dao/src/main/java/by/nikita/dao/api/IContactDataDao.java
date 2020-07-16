package by.nikita.dao.api;

import by.nikita.models.ContactData;

import java.util.List;

public interface IContactDataDao extends IAbstractGenericDao<ContactData> {

    List<ContactData> getContactDataByUserFirstName(String userFirstName);

    List<ContactData> getContactDataByUserLastName(String userLastName);

}
