package by.nikita.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role_table")
public class Role extends AEntity {

    private String name;
}
