package by.nikita.dao.api;

import by.nikita.dao.AGenericDao;
import by.nikita.models.User;

public class UserDao extends AGenericDao<User> implements IUserDao {
    public UserDao() {
        super(User.class);
    }
}
