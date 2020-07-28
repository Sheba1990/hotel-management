package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoleDto> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userFirstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userMiddleName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static List<UserDto> convertList(List<User> userList) {
        List<UserDto> users = new ArrayList<>();
        List<RoleDto> roles = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUsername());
            userDto.setUserEmail(user.getEmail());
            for (Role role : user.getRoles()) {
                RoleDto roleDto = new RoleDto();
                if (user.getRoles() != null) {
                    roleDto.setRoleName(role.getRoleName());
                    roles.add(roleDto);
                } else {
                    userDto.setRoles(null);
                }
            }
            userDto.setRoles(roles);
            users.add(userDto);
        }
        return users;
    }

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        List<RoleDto> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            RoleDto roleDto = new RoleDto();
            if (user.getRoles() != null) {
                roleDto.setRoleName(role.getRoleName());
                roles.add(roleDto);
            } else {
                userDto.setRoles(null);
            }
        }
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setUserEmail(user.getEmail());
        userDto.setRoles(roles);
        userDto.setUserFirstName(user.getUserDetails().getFirstName());
        userDto.setUserMiddleName(user.getUserDetails().getMiddleName());
        userDto.setUserLastName(user.getUserDetails().getLastName());
        userDto.setUserBirthDate(user.getUserDetails().getBirthDate());
        userDto.setUserPassportNumber(user.getUserDetails().getPassportData().getPassportNumber());
        userDto.setUserPassportCountryOfIssue(user.getUserDetails().getPassportData().getCountryOfIssue());
        userDto.setUserPassportDateOfIssue(user.getUserDetails().getPassportData().getDateOfIssue());
        userDto.setUserPassportDateOfExpiry(user.getUserDetails().getPassportData().getDateOfExpiry());
        userDto.setUserPhoneNumber(user.getUserDetails().getContactData().getPhoneNumber());
        userDto.setUserPostalCode(user.getUserDetails().getContactData().getAddress().getPostalCode());
        userDto.setUserResidenceCountry(user.getUserDetails().getContactData().getAddress().getCountry());
        userDto.setUserResidenceProvince(user.getUserDetails().getContactData().getAddress().getProvince());
        userDto.setUserResidenceCity(user.getUserDetails().getContactData().getAddress().getCity());
        userDto.setUserResidenceStreet(user.getUserDetails().getContactData().getAddress().getStreet());
        userDto.setUserResidenceHomeNumber(user.getUserDetails().getContactData().getAddress().getHomeNumber());
        userDto.setUserResidenceApartmentNumber(user.getUserDetails().getContactData().getAddress().getApartmentNumber());
        return userDto;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.roles = RoleDto.convertList(user.getRoles());
        this.userEmail = user.getEmail();
        this.userFirstName = user.getUserDetails().getFirstName();
        this.userLastName = user.getUserDetails().getLastName();
        this.userMiddleName = user.getUserDetails().getMiddleName();
        this.userBirthDate = user.getUserDetails().getBirthDate();
        this.userPassportNumber = user.getUserDetails().getPassportData().getPassportNumber();
        this.userPassportCountryOfIssue = user.getUserDetails().getPassportData().getCountryOfIssue();
        this.userPassportDateOfIssue = user.getUserDetails().getPassportData().getDateOfIssue();
        this.userPassportDateOfExpiry = user.getUserDetails().getPassportData().getDateOfExpiry();
        this.userPhoneNumber = user.getUserDetails().getContactData().getPhoneNumber();
        this.userPostalCode = user.getUserDetails().getContactData().getAddress().getPostalCode();
        this.userResidenceCountry = user.getUserDetails().getContactData().getAddress().getCountry();
        this.userResidenceProvince = user.getUserDetails().getContactData().getAddress().getProvince();
        this.userResidenceCity = user.getUserDetails().getContactData().getAddress().getCity();
        this.userResidenceStreet = user.getUserDetails().getContactData().getAddress().getStreet();
        this.userResidenceHomeNumber = user.getUserDetails().getContactData().getAddress().getHomeNumber();
        this.userResidenceApartmentNumber = user.getUserDetails().getContactData().getAddress().getApartmentNumber();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
}
