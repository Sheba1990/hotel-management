package by.nikita.dao.api;

import by.nikita.models.User;

import java.util.List;

public interface IUserDao extends IAbstractGenericDao<User> {

    List<User> getUsersByFirstName(String firstName);

    List<User> getUsersByLastName(String lastName);

    List<User> getUsersByPassportIssueCountry(String passportCountry);
}
