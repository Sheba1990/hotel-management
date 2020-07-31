package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.UserDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDto extends AbstractIdAwareDto {

    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoleDto> userRoles;

    private String userFirstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userMiddleName;

    private String userLastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate userBirthDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassportNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassportCountryOfIssue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassportDateOfIssue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassportDateOfExpiry;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPostalCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceCountry;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceProvince;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceCity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceStreet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceHomeNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userResidenceApartmentNumber;

    public static List<UserDetailsDto> convertList(List<UserDetails> userDetailsList) {
        List<UserDetailsDto> userDetailss = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setId(userDetails.getId());
            userDetailsDto.setUserName(userDetails.getUser().getUsername());
            userDetailsDto.setUserFirstName(userDetails.getFirstName());
            userDetailsDto.setUserMiddleName(userDetails.getMiddleName());
            userDetailsDto.setUserLastName(userDetails.getLastName());
            userDetailss.add(userDetailsDto);
        }
        return userDetailss;
    }

    public static UserDetailsDto entityToDto(UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        List<RoleDto> roles = new ArrayList<>();
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
        userDetailsDto.setUserBirthDate(userDetails.getBirthDate());
        userDetailsDto.setUserPassportNumber(userDetails.getPassportData().getPassportNumber());
        userDetailsDto.setUserPassportCountryOfIssue(userDetails.getPassportData().getCountryOfIssue());
        userDetailsDto.setUserPassportDateOfIssue(userDetails.getPassportData().getDateOfIssue());
        userDetailsDto.setUserPassportDateOfExpiry(userDetails.getPassportData().getDateOfExpiry());
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
        this.userBirthDate = userDetails.getBirthDate();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(LocalDate userBirthDate) {
        this.userBirthDate = userBirthDate;
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

    public List<RoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleDto> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
