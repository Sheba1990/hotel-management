package by.nikita.dao.api;

import by.nikita.models.User;

public interface IUserDao extends IAbstractGenericDao<User> {

    User getByUsername(String username);

    boolean checkUsernamePresence(String username);


}
