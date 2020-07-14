package by.nikita.dao;

import by.nikita.dao.api.IContactDataDao;
import by.nikita.models.ContactData;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDataDao extends AbstractGenericDao<ContactData> implements IContactDataDao {
    public ContactDataDao() {
        super(ContactData.class);
    }
}
