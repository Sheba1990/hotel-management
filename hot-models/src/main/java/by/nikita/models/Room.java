package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_table")
public class Room extends AbstractIdAwareEntity {

    @Column(name = "room_number")
    private Integer roomNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_category_id", referencedColumnName = "id")
    private RoomCategory roomCategory;

    @ManyToOne
    @JoinColumn(name = "room_details_id", referencedColumnName = "id")
    private RoomDetails roomDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_status_id", referencedColumnName = "id")
    private RoomStatus roomStatus;

    @OneToMany(mappedBy = "room")
    private List<Order> orders;

    public Room() {
    }

    public Room(Integer roomNumber, RoomCategory roomCategory, RoomDetails roomDetails, RoomStatus roomStatus, List<Order> orders) {
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.roomDetails = roomDetails;
        this.roomStatus = roomStatus;
        this.orders = orders;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

