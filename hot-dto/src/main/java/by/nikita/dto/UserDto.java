package by.nikita.dto;

import by.nikita.models.User;
import by.nikita.models.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto extends AbstractIdAwareDto {

    private String userName;

    private Set<RoleDto> roles;

    private String userEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassword;

    private String userFirstName;

    private String userLastName;


    public static List<UserDto> convertList(List<User> userList) {
        List<UserDto> users = new ArrayList<>();
        for (User user : userList) {
            Set<RoleDto> roles = new HashSet<>();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUsername());
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
            userDto.setUserEmail(user.getEmail());
            userDto.setUserFirstName(user.getUserDetails().getFirstName());
            userDto.setUserLastName(user.getUserDetails().getLastName());
            users.add(userDto);
        }
        return users;
    }

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        Set<RoleDto> roles = new HashSet<>();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        for (Role role : user.getRoles()) {
            RoleDto roleDto = new RoleDto();
            if (user.getRoles() != null) {
                roleDto.setRoleName(role.toString());
                roles.add(roleDto);
            } else {
                userDto.setRoles(null);
            }
        }
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setRoles(roles);
        userDto.setUserFirstName(user.getUserDetails().getFirstName());
        userDto.setUserLastName(user.getUserDetails().getLastName());
        userDto.setUserEmail(user.getEmail());
        return userDto;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.userEmail = user.getEmail();
        this.userFirstName = user.getUserDetails().getFirstName();
        this.userLastName = user.getUserDetails().getLastName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
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

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
