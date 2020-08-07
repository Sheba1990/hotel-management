package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_category_table")
public class RoomCategory extends AbstractIdAwareEntity {

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomCategory() {
    }

    public RoomCategory(String categoryName, String description, List<Room> rooms) {
        this.categoryName = categoryName;
        this.description = description;
        this.rooms = rooms;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
