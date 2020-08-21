package by.nikita.dao.api;

import by.nikita.models.User;
import org.springframework.data.domain.Example;

import java.util.List;

public interface IUserDao extends IAbstractGenericDao<User> {

    List<User> getUsersByFirstName(String firstName);

    List<User> getUsersByLastName(String lastName);

    List<User> getUsersByFullName(String firstName, String lastName);

    List<User> getUsersByPassportIssueCountry(String passportCountry);

    List<User> getUsersByResidenceCountry(String residenceCountry);

    List<User> getUsersByResidenceCity(String residenceCity);

    List<User> getUsersByOccupiedRoomNumber(Integer roomNumber);

}
