package by.nikita.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends AbstractIdAwareEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirmation;

    private boolean active;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserInDetails userInDetails;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role_table ", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public User() {
    }

    public User(String username,
                String email,
                String password,
                String passwordConfirmation,
                boolean active,
                Order order,
                UserInDetails userInDetails,
                Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.active = active;
        this.order = order;
        this.userInDetails = userInDetails;
        this.roles = roles;
    }


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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserInDetails getUserInDetails() {
        return userInDetails;
    }

    public void setUserInDetails(UserInDetails userInDetails) {
        this.userInDetails = userInDetails;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

