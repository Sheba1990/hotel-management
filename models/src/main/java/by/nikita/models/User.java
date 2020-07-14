package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User extends AEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "activation_code")
    private String activationCode;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> bookings;

    @OneToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    public User() {
    }

    public User(String username, String email, String password, String activationCode, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.activationCode = activationCode;
        this.role = role;
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getBookings() {
        return bookings;
    }

    public void setBookings(List<Order> bookings) {
        this.bookings = bookings;
    }
}

