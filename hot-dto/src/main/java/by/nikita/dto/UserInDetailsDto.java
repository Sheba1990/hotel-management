package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.UserInDetails;
import by.nikita.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInDetailsDto extends AbstractIdAwareDto {

    private String userName;

    private Set<RoleDto> userRoles;

    private String userFirstName;

    private String userMiddleName;

    private String userLastName;

    private String userFullName;

    private LocalDate userBirthDate;

    private Long age;

    private Gender gender;

    private String userPassportNumber;

    private String userPassportCountryOfIssue;

    private String userPassportDateOfIssue;

    private String userPassportDateOfExpiry;

    private String userPhoneNumber;

    private String userEmail;

    private String userPostalCode;

    private String userResidenceCountry;

    private String userResidenceProvince;

    private String userResidenceCity;

    private String userResidenceStreet;

    private String userResidenceHomeNumber;

    private String userResidenceApartmentNumber;

    public static List<UserInDetailsDto> convertList(List<UserInDetails> userInDetailsList) {
        List<UserInDetailsDto> userDetailss = new ArrayList<>();
        for (UserInDetails userInDetails : userInDetailsList) {
            UserInDetailsDto userInDetailsDto = new UserInDetailsDto();
            userInDetailsDto.setId(userInDetails.getId());
            userInDetailsDto.setUserName(userInDetails.getUser().getUsername());
            if (userInDetails.getMiddleName() != null && !StringUtils.isEmpty(userInDetails.getMiddleName())) {
                userInDetailsDto.setUserFullName(userInDetails.getFirstName().concat(" " + userInDetails.getMiddleName() + " " + userInDetails.getLastName()));
            } else {
                userInDetailsDto.setUserFullName(userInDetails.getFirstName().concat(" " + userInDetails.getLastName()));
            }
            userDetailss.add(userInDetailsDto);
        }
        return userDetailss;
    }

    public static UserInDetailsDto entityToDto(UserInDetails userInDetails) {
        UserInDetailsDto userInDetailsDto = new UserInDetailsDto();
        Set<RoleDto> roles = new HashSet<>();
        userInDetailsDto.setId(userInDetails.getId());
        userInDetailsDto.setUserName(userInDetails.getUser().getUsername());
        for (Role role : userInDetails.getUser().getRoles()) {
            RoleDto roleDto = new RoleDto();
            if (userInDetails.getUser().getRoles() != null) {
                roleDto.setRoleName(role.toString());
                roles.add(roleDto);
            } else {
                userInDetailsDto.setUserRoles(null);
            }
        }
        userInDetailsDto.setUserRoles(roles);
        userInDetailsDto.setUserFirstName(userInDetails.getFirstName());
        userInDetailsDto.setUserMiddleName(userInDetails.getMiddleName());
        userInDetailsDto.setUserLastName(userInDetails.getLastName());
        userInDetailsDto.setGender(userInDetails.getGender());
        userInDetailsDto.setUserBirthDate(userInDetails.getBirthDate());
        if (userInDetailsDto.getUserBirthDate() != null) {
            LocalDate date1 = userInDetailsDto.getUserBirthDate();
            LocalDate date2 = LocalDate.now();
            Long amountOfYears = ChronoUnit.YEARS.between(date1, date2);
            userInDetailsDto.setAge(amountOfYears);
        }
        if (userInDetails.getPassportData() != null) {
            userInDetailsDto.setUserPassportNumber(userInDetails.getPassportData().getPassportNumber());
            userInDetailsDto.setUserPassportCountryOfIssue(userInDetails.getPassportData().getCountryOfIssue());
            userInDetailsDto.setUserPassportDateOfIssue(userInDetails.getPassportData().getDateOfIssue());
            userInDetailsDto.setUserPassportDateOfExpiry(userInDetails.getPassportData().getDateOfExpiry());
        }
        userInDetailsDto.setUserPhoneNumber(userInDetails.getContactData().getPhoneNumber());
        userInDetailsDto.setUserEmail(userInDetails.getUser().getEmail());
        userInDetailsDto.setUserPostalCode(userInDetails.getContactData().getAddress().getPostalCode());
        userInDetailsDto.setUserResidenceCountry(userInDetails.getContactData().getAddress().getCountry());
        userInDetailsDto.setUserResidenceProvince(userInDetails.getContactData().getAddress().getProvince());
        userInDetailsDto.setUserResidenceCity(userInDetails.getContactData().getAddress().getCity());
        userInDetailsDto.setUserResidenceStreet(userInDetails.getContactData().getAddress().getStreet());
        userInDetailsDto.setUserResidenceHomeNumber(userInDetails.getContactData().getAddress().getHomeNumber());
        userInDetailsDto.setUserResidenceApartmentNumber(userInDetails.getContactData().getAddress().getApartmentNumber());
        return userInDetailsDto;
    }

    public UserInDetailsDto() {
    }

    public UserInDetailsDto(UserInDetails userInDetails) {
        this.id = userInDetails.getId();
        this.userName = userInDetails.getUser().getUsername();
        this.userFirstName = userInDetails.getFirstName();
        this.userMiddleName = userInDetails.getMiddleName();
        this.userLastName = userInDetails.getLastName();
        this.gender = userInDetails.getGender();
        this.userBirthDate = userInDetails.getBirthDate();
        this.userPassportNumber = userInDetails.getPassportData().getPassportNumber();
        this.userPassportCountryOfIssue = userInDetails.getPassportData().getCountryOfIssue();
        this.userPassportDateOfIssue = userInDetails.getPassportData().getDateOfIssue();
        this.userPassportDateOfExpiry = userInDetails.getPassportData().getDateOfExpiry();
        this.userPhoneNumber = userInDetails.getContactData().getPhoneNumber();
        this.userEmail = userInDetails.getUser().getEmail();
        this.userPostalCode = userInDetails.getContactData().getAddress().getPostalCode();
        this.userResidenceCountry = userInDetails.getContactData().getAddress().getCountry();
        this.userResidenceProvince = userInDetails.getContactData().getAddress().getProvince();
        this.userResidenceCity = userInDetails.getContactData().getAddress().getCity();
        this.userResidenceStreet = userInDetails.getContactData().getAddress().getStreet();
        this.userResidenceHomeNumber = userInDetails.getContactData().getAddress().getHomeNumber();
        this.userResidenceApartmentNumber = userInDetails.getContactData().getAddress().getApartmentNumber();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<RoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleDto> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(LocalDate userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUserPassportNumber() {
        return userPassportNumber;
    }

    public void setUserPassportNumber(String userPassportNumber) {
        this.userPassportNumber = userPassportNumber;
    }

    public String getUserPassportCountryOfIssue() {
        return userPassportCountryOfIssue;
    }

    public void setUserPassportCountryOfIssue(String userPassportCountryOfIssue) {
        this.userPassportCountryOfIssue = userPassportCountryOfIssue;
    }

    public String getUserPassportDateOfIssue() {
        return userPassportDateOfIssue;
    }

    public void setUserPassportDateOfIssue(String userPassportDateOfIssue) {
        this.userPassportDateOfIssue = userPassportDateOfIssue;
    }

    public String getUserPassportDateOfExpiry() {
        return userPassportDateOfExpiry;
    }

    public void setUserPassportDateOfExpiry(String userPassportDateOfExpiry) {
        this.userPassportDateOfExpiry = userPassportDateOfExpiry;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPostalCode() {
        return userPostalCode;
    }

    public void setUserPostalCode(String userPostalCode) {
        this.userPostalCode = userPostalCode;
    }

    public String getUserResidenceCountry() {
        return userResidenceCountry;
    }

    public void setUserResidenceCountry(String userResidenceCountry) {
        this.userResidenceCountry = userResidenceCountry;
    }

    public String getUserResidenceProvince() {
        return userResidenceProvince;
    }

    public void setUserResidenceProvince(String userResidenceProvince) {
        this.userResidenceProvince = userResidenceProvince;
    }

    public String getUserResidenceCity() {
        return userResidenceCity;
    }

    public void setUserResidenceCity(String userResidenceCity) {
        this.userResidenceCity = userResidenceCity;
    }

    public String getUserResidenceStreet() {
        return userResidenceStreet;
    }

    public void setUserResidenceStreet(String userResidenceStreet) {
        this.userResidenceStreet = userResidenceStreet;
    }

    public String getUserResidenceHomeNumber() {
        return userResidenceHomeNumber;
    }

    public void setUserResidenceHomeNumber(String userResidenceHomeNumber) {
        this.userResidenceHomeNumber = userResidenceHomeNumber;
    }

    public String getUserResidenceApartmentNumber() {
        return userResidenceApartmentNumber;
    }

    public void setUserResidenceApartmentNumber(String userResidenceApartmentNumber) {
        this.userResidenceApartmentNumber = userResidenceApartmentNumber;
    }


}
