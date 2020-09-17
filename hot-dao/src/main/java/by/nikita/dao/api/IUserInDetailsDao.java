package by.nikita.dao.api;

import by.nikita.models.UserInDetails;

import java.util.List;

public interface IUserInDetailsDao extends IAbstractGenericDao<UserInDetails> {

    UserInDetails getUserDetailsByUserId(long userId);

    UserInDetails getUserDetailsByUsername(String username);

    List<UserInDetails> getUserDetailsByUserFirstName(String firstName);

    List<UserInDetails> getUserDetailsByUserLastName(String lastName);

    List<UserInDetails> getUserDetailsByFullName(String firstName, String lastName);

    List<UserInDetails> getUsersByPassportIssueCountry(String passportCountry);

    List<UserInDetails> getUsersByResidenceCountry(String residenceCountry);

    List<UserInDetails> getUsersByResidenceCity(String residenceCity);

    UserInDetails getUserByOccupiedRoomNumber(Integer roomNumber);
}
