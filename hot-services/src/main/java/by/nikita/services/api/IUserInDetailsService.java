package by.nikita.services.api;

import by.nikita.dto.AddressDto;
import by.nikita.dto.ContactDataDto;
import by.nikita.dto.PassportDataDto;
import by.nikita.dto.UserInDetailsDto;

import java.util.List;

public interface IUserInDetailsService {

    UserInDetailsDto addUserDetails(UserInDetailsDto userInDetailsDto, ContactDataDto contactDataDto, AddressDto addressDto, PassportDataDto passportDataDto);

    UserInDetailsDto getUserDetailsById(long id);

    UserInDetailsDto getUserDetailsByUserId(long userId);

    List<UserInDetailsDto> getAllUserDetails();

    List<UserInDetailsDto> getUserDetailsByUserFirstName(String firstName);

    List<UserInDetailsDto> getUserDetailsByUserLastName(String lastName);

    List<UserInDetailsDto> getUserDetailsByFullName(String firstName, String lastName);

    List<UserInDetailsDto> getUsersByPassportIssueCountry(String passportCountry);

    List<UserInDetailsDto> getUsersByResidenceCountry(String residenceCountry);

    List<UserInDetailsDto> getUsersByResidenceCity(String residenceCity);

    UserInDetailsDto getUserByOccupiedRoomNumber(Integer roomNumber);

    void updateUserDetails(long id, UserInDetailsDto userInDetailsDto, ContactDataDto contactDataDto, AddressDto addressDto, PassportDataDto passportDataDto);

    void deleteUserDetails(long id);

    UserInDetailsDto addContactDataToUserDetails(long contactDataId, long userDetailsId);

    UserInDetailsDto addPassportDataToUserDetails(long passportDataId, long userDetailsId);
}
