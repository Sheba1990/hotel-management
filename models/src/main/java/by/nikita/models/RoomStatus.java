package by.nikita.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "room_status_table")
public class RoomStatus extends AEntity {

    private String name;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomStatus() {
    }

    public RoomStatus(String name, List<Room> rooms) {
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
