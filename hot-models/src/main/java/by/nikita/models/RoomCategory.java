package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_category_table")
public class RoomCategory extends AbstractIdAwareEntity {

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomCategory() {
    }

    public RoomCategory(String categoryName, List<Room> rooms) {
        this.categoryName = categoryName;
        this.rooms = rooms;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
