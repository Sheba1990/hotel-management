package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_category_table")
public class Category extends AEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public Category() {
    }

    public Category(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
