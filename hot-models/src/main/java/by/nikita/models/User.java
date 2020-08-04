package by.nikita.models;

import by.nikita.models.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class User extends AbstractIdAwareEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role_table ", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public User() {
    }

    public User(Long id,
                String username,
                String email,
                String password,
                boolean active,
                Order order,
                UserDetails userDetails,
                Set<Role> roles) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.order = order;
        this.userDetails = userDetails;
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

