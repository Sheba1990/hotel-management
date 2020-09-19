package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.User;
import by.nikita.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AbstractIdAwareDto {

    private String username;

    private Set<String> roles;

    @Email
    private String email;

    @Length(min = 5, message = "Password can not be less than 5 symbols")
    private String password;

    private String userPasswordConfirmation;

    private String userFirstName;

    private String userMiddleName;

    private String userLastName;

    private String userFullName;

    private String fileName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthDate;

    private Long age;

    private Gender gender;

    private String userPassportNumber;

    private String userPassportCountryOfIssue;

    private String userPassportDateOfIssue;

    private String userPassportDateOfExpiry;

    private String userPhoneNumber;

    private String userPostalCode;

    private String userResidenceCountry;

    private String userResidenceProvince;

    private String userResidenceCity;

    private String userResidenceStreet;

    private String userResidenceHomeNumber;

    private String userResidenceApartmentNumber;

    public static List<UserDto> convertList(List<User> userList) {
        List<UserDto> users = new ArrayList<>();
        for (User user : userList) {
            Set<String> roles = new HashSet<>();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            for (Role role : user.getRoles()) {
                String roleDto;
                if (user.getRoles() != null) {
                    roleDto = (role.toString());
                    roles.add(roleDto);
                } else {
                    userDto.setRoles(null);
                }
            }
            userDto.setRoles(roles);
            userDto.setEmail(user.getEmail());
            if (user.getUserInDetails().getFirstName() != null
                    && user.getUserInDetails().getLastName() != null
                    && user.getUserInDetails().getMiddleName() != null
            ) {
                if (user.getUserInDetails().getMiddleName() != null && !StringUtils.isEmpty(user.getUserInDetails().getMiddleName())) {
                    userDto.setUserFullName(user.getUserInDetails().getFirstName().concat(" " + user.getUserInDetails().getMiddleName() + " " + user.getUserInDetails().getLastName()));
                } else {
                    userDto.setUserFullName(user.getUserInDetails().getFirstName().concat(" " + user.getUserInDetails().getLastName()));
                }
            } else {
                userDto.setUserFullName(null);
            }
            if (user.getUserInDetails().getContactData().getAddress().getCountry() != null) {
                userDto.setUserResidenceCountry(user.getUserInDetails().getContactData().getAddress().getCountry());
            }
            users.add(userDto);
        }
        return users;
    }

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        Set<String> roles = new HashSet<>();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        for (Role role : user.getRoles()) {
            String roleDto;
            if (user.getRoles() != null) {
                roleDto = (role.toString());
                roles.add(roleDto);
            } else {
                userDto.setRoles(null);
            }
        }
        userDto.setRoles(roles);
        userDto.setEmail(user.getEmail());
        if (user.getUserInDetails().getFirstName() != null
                && user.getUserInDetails().getLastName() != null
                && user.getUserInDetails().getMiddleName() != null
        ) {
            userDto.setUserFirstName(user.getUserInDetails().getFirstName());
            userDto.setUserMiddleName(user.getUserInDetails().getMiddleName());
            userDto.setUserLastName(user.getUserInDetails().getLastName());
        }
        if (user.getUserInDetails().getFileName() != null) {
            userDto.setFileName(user.getUserInDetails().getFileName());
        }
        if (user.getUserInDetails().getGender() != null) {
            userDto.setGender(user.getUserInDetails().getGender());
        }
        userDto.setUserBirthDate(user.getUserInDetails().getBirthDate());
        if (userDto.getUserBirthDate() != null) {
            LocalDate date1 = userDto.getUserBirthDate();
            LocalDate date2 = LocalDate.now();
            Long userAge = ChronoUnit.YEARS.between(date1, date2);
            userDto.setAge(userAge);
        }
        if (user.getUserInDetails().getPassportData() != null) {
            userDto.setUserPassportNumber(user.getUserInDetails().getPassportData().getPassportNumber());
            userDto.setUserPassportCountryOfIssue(user.getUserInDetails().getPassportData().getCountryOfIssue());
            userDto.setUserPassportDateOfIssue(user.getUserInDetails().getPassportData().getDateOfIssue());
            userDto.setUserPassportDateOfExpiry(user.getUserInDetails().getPassportData().getDateOfExpiry());
        }
        if (user.getUserInDetails().getContactData().getPhoneNumber() != null) {
            userDto.setUserPhoneNumber(user.getUserInDetails().getContactData().getPhoneNumber());
        } else {
            userDto.setUserPhoneNumber(null);
        }
        if (user.getUserInDetails().getContactData().getAddress() != null) {
            userDto.setUserPostalCode(user.getUserInDetails().getContactData().getAddress().getPostalCode());
            userDto.setUserResidenceCountry(user.getUserInDetails().getContactData().getAddress().getCountry());
            userDto.setUserResidenceProvince(user.getUserInDetails().getContactData().getAddress().getProvince());
            userDto.setUserResidenceCity(user.getUserInDetails().getContactData().getAddress().getCity());
            userDto.setUserResidenceStreet(user.getUserInDetails().getContactData().getAddress().getStreet());
            userDto.setUserResidenceHomeNumber(user.getUserInDetails().getContactData().getAddress().getHomeNumber());
            userDto.setUserResidenceApartmentNumber(user.getUserInDetails().getContactData().getAddress().getApartmentNumber());
        }
        return userDto;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fileName = user.getUserInDetails().getFileName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPasswordConfirmation() {
        return userPasswordConfirmation;
    }

    public void setUserPasswordConfirmation(String userPasswordConfirmation) {
        this.userPasswordConfirmation = userPasswordConfirmation;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
