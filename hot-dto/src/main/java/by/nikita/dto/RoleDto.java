package by.nikita.dto;

import by.nikita.models.Role;
import by.nikita.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class RoleDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserDto> users;

    public static List<RoleDto> convertList(List<Role> roleList) {
        List<RoleDto> roles = new ArrayList<>();
        List<UserDto> users = new ArrayList<>();
        for (Role role : roleList) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setRoleName(role.getRoleName());
            for (User user : role.getUsers()) {
                UserDto userDto = new UserDto();
                if (role.getUsers() != null) {
                    userDto.setId(user.getId());
                    userDto.setUserName(user.getUsername());
                    users.add(userDto);
                } else {
                    roleDto.setUsers(null);
                }
            }
            roleDto.setUsers(users);
            roles.add(roleDto);
        }
        return roles;
    }

    public static RoleDto roleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        List<UserDto> users = new ArrayList<>();
        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());
        for (User user : role.getUsers()) {
            UserDto userDto = new UserDto();
            if (role.getUsers() != null) {
                userDto.setId(user.getId());
                userDto.setUserName(user.getUsername());
                userDto.setUserFirstName(user.getUserDetails().getFirstName());
                userDto.setUserLastName(user.getUserDetails().getLastName());
                users.add(userDto);
            } else {
                roleDto.setUsers(null);
            }
        }
        roleDto.setUsers(users);
        return roleDto;
    }

    public RoleDto() {

    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.users = UserDto.convertList(role.getUsers());
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
