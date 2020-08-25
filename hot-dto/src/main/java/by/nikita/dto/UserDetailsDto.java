package by.nikita.dto;

import by.nikita.models.UserDetails;
import by.nikita.models.enums.Gender;
import by.nikita.models.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsDto extends AbstractIdAwareDto {

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

    public static List<UserDetailsDto> convertList(List<UserDetails> userDetailsList) {
        List<UserDetailsDto> userDetailss = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setId(userDetails.getId());
            userDetailsDto.setUserName(userDetails.getUser().getUsername());
            if (userDetails.getMiddleName() != null && !StringUtils.isEmpty(userDetails.getMiddleName())) {
                userDetailsDto.setUserFullName(userDetails.getFirstName().concat(" " + userDetails.getMiddleName() + " " + userDetails.getLastName()));
            } else {
                userDetailsDto.setUserFullName(userDetails.getFirstName().concat(" " + userDetails.getLastName()));
            }
            userDetailss.add(userDetailsDto);
        }
        return userDetailss;
    }

    public static UserDetailsDto entityToDto(UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        Set<RoleDto> roles = new HashSet<>();
        userDetailsDto.setId(userDetails.getId());
        userDetailsDto.setUserName(userDetails.getUser().getUsername());
        for (Role role : userDetails.getUser().getRoles()) {
            RoleDto roleDto = new RoleDto();
            if (userDetails.getUser().getRoles() != null) {
                roleDto.setRoleName(role.toString());
                roles.add(roleDto);
            } else {
                userDetailsDto.setUserRoles(null);
            }
        }
        userDetailsDto.setUserRoles(roles);
        userDetailsDto.setUserFirstName(userDetails.getFirstName());
        userDetailsDto.setUserMiddleName(userDetails.getMiddleName());
        userDetailsDto.setUserLastName(userDetails.getLastName());
        userDetailsDto.setGender(userDetails.getGender());
        userDetailsDto.setUserBirthDate(userDetails.getBirthDate());
        if (userDetailsDto.getUserBirthDate() != null) {
            LocalDate date1 = userDetailsDto.userBirthDate;
            LocalDate date2 = LocalDate.now();
            Long amountOfYears = ChronoUnit.YEARS.between(date1, date2);
            userDetailsDto.setAge(amountOfYears);
        }
        if (userDetails.getPassportData() != null) {
            userDetailsDto.setUserPassportNumber(userDetails.getPassportData().getPassportNumber());
            userDetailsDto.setUserPassportCountryOfIssue(userDetails.getPassportData().getCountryOfIssue());
            userDetailsDto.setUserPassportDateOfIssue(userDetails.getPassportData().getDateOfIssue());
            userDetailsDto.setUserPassportDateOfExpiry(userDetails.getPassportData().getDateOfExpiry());
        }
        userDetailsDto.setUserPhoneNumber(userDetails.getContactData().getPhoneNumber());
        userDetailsDto.setUserEmail(userDetails.getUser().getEmail());
        userDetailsDto.setUserPostalCode(userDetails.getContactData().getAddress().getPostalCode());
        userDetailsDto.setUserResidenceCountry(userDetails.getContactData().getAddress().getCountry());
        userDetailsDto.setUserResidenceProvince(userDetails.getContactData().getAddress().getProvince());
        userDetailsDto.setUserResidenceCity(userDetails.getContactData().getAddress().getCity());
        userDetailsDto.setUserResidenceStreet(userDetails.getContactData().getAddress().getStreet());
        userDetailsDto.setUserResidenceHomeNumber(userDetails.getContactData().getAddress().getHomeNumber());
        userDetailsDto.setUserResidenceApartmentNumber(userDetails.getContactData().getAddress().getApartmentNumber());
        return userDetailsDto;
    }

    public UserDetailsDto() {
    }

    public UserDetailsDto(UserDetails userDetails) {
        this.id = userDetails.getId();
        this.userName = userDetails.getUser().getUsername();
        this.userFirstName = userDetails.getFirstName();
        this.userMiddleName = userDetails.getMiddleName();
        this.userLastName = userDetails.getLastName();
        this.gender = userDetails.getGender();
        this.userBirthDate = userDetails.getBirthDate();
        this.userPassportNumber = userDetails.getPassportData().getPassportNumber();
        this.userPassportCountryOfIssue = userDetails.getPassportData().getCountryOfIssue();
        this.userPassportDateOfIssue = userDetails.getPassportData().getDateOfIssue();
        this.userPassportDateOfExpiry = userDetails.getPassportData().getDateOfExpiry();
        this.userPhoneNumber = userDetails.getContactData().getPhoneNumber();
        this.userEmail = userDetails.getUser().getEmail();
        this.userPostalCode = userDetails.getContactData().getAddress().getPostalCode();
        this.userResidenceCountry = userDetails.getContactData().getAddress().getCountry();
        this.userResidenceProvince = userDetails.getContactData().getAddress().getProvince();
        this.userResidenceCity = userDetails.getContactData().getAddress().getCity();
        this.userResidenceStreet = userDetails.getContactData().getAddress().getStreet();
        this.userResidenceHomeNumber = userDetails.getContactData().getAddress().getHomeNumber();
        this.userResidenceApartmentNumber = userDetails.getContactData().getAddress().getApartmentNumber();
    }


    //Getters & Setters


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
