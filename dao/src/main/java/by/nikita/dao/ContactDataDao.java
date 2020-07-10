package by.nikita.dao;

import by.nikita.dao.api.IContactDataDao;
import by.nikita.models.ContactData;

public class ContactDataDao extends AGenericDao<ContactData> implements IContactDataDao {
    public ContactDataDao() {
        super(ContactData.class);
    }
}
