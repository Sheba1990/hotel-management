package by.nikita.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_table")
public enum Role implements GrantedAuthority {

    GUEST, ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
