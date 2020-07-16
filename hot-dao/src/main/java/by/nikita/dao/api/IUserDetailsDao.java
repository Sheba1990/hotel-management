package by.nikita.dao.api;

import by.nikita.models.UserDetails;

import java.util.List;

public interface IUserDetailsDao extends IAbstractGenericDao<UserDetails> {

    List<UserDetails> getUserDetailsByUserFirstName(String firstName);
}
