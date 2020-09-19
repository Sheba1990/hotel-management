package by.nikita.dao.api;

import by.nikita.models.User;

import java.util.List;

public interface IUserDao extends IAbstractGenericDao<User> {

    User getByUsername(String username);

    boolean checkUsernamePresence(String username);

    List<User> getUsersByUserFirstName(String firstName);

    List<User> getUsersByUserLastName(String lastName);

    List<User> getUsersByFullName(String firstName, String lastName, String middleName);

    List<User> getUsersByPassportIssueCountry(String passportCountry);

    List<User> getUsersByResidenceCountry(String residenceCountry);

    List<User> getUsersByResidenceCity(String residenceCity);

    User getUserByOccupiedRoomNumber(Integer roomNumber);

}
