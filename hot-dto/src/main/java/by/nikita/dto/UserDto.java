package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AbstractIdAwareDto {

    private String username;

    private Set<RoleDto> roles;

    @Email
    private String email;

    @Length(min = 5, message = "Password can not be less than 5 symbols")
    private String password;

    private String userPasswordConfirmation;

    private String userFirstName;

    private String userLastName;


    public static List<UserDto> convertList(List<User> userList) {
        List<UserDto> users = new ArrayList<>();
        for (User user : userList) {
            Set<RoleDto> roles = new HashSet<>();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            for (Role role : user.getRoles()) {
                RoleDto roleDto = new RoleDto();
                if (user.getRoles() != null) {
                    roleDto.setRoleName(role.toString());
                    roles.add(roleDto);
                } else {
                    userDto.setRoles(null);
                }
            }
            userDto.setRoles(roles);
            userDto.setEmail(user.getEmail());
            users.add(userDto);
        }
        return users;
    }

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        Set<RoleDto> roles = new HashSet<>();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        for (Role role : user.getRoles()) {
            RoleDto roleDto = new RoleDto();
            if (user.getRoles() != null) {
                roleDto.setRoleName(role.toString());
                roles.add(roleDto);
            } else {
                userDto.setRoles(null);
            }
        }
        userDto.setRoles(roles);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
