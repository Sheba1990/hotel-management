package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_status_table")
public class RoomStatus extends AbstractIdAwareEntity {

    @Column(name = "status_name")
    private String statusName;

    @OneToMany(mappedBy = "roomStatus", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomStatus() {
    }

    public RoomStatus(String statusName, List<Room> rooms) {
        this.statusName = statusName;
        this.rooms = rooms;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
