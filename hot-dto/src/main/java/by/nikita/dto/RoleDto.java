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
    private List<User> users;

    public static List<RoleDto> convertList(List<Role> roleList) {
        List<RoleDto> roles = new ArrayList<>();
        for (Role role : roleList) {
            List<UserDto> users = new ArrayList<>();
        }
    }

    public RoleDto() {

    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.users = role.getUsers();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
