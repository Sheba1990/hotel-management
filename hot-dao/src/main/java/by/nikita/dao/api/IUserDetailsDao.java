package by.nikita.dao.api;

import by.nikita.models.UserDetails;

import java.util.List;

public interface IUserDetailsDao extends IAbstractGenericDao<UserDetails> {

    UserDetails getUserDetailsByUserId(long userId);

    List<UserDetails> getUserDetailsByUserFirstName(String firstName);

    List<UserDetails> getUserDetailsByUserLastName(String lastName);
}
