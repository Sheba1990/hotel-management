package by.nikita.models;

import by.nikita.models.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class User extends AbstractIdAwareEntity {

    @Column(name = "username")
    @NotBlank(message = "Username can not be empty")
    private String username;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email can not be empty")
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password can not be empty")
    private String password;

    private boolean active;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role_table ", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    //Constructors

    public User() {
    }

    public User(String username,
                String email,
                String password,
                boolean active,
                Order order,
                UserDetails userDetails,
                Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.order = order;
        this.userDetails = userDetails;
        this.roles = roles;
    }


    //Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

