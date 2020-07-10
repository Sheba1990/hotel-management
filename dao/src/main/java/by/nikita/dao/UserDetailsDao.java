package by.nikita.dao;

import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.models.UserDetails;

public class UserDetailsDao extends AGenericDao<UserDetails> implements IUserDetailsDao {
    public UserDetailsDao() {
        super(UserDetails.class);
    }
}
