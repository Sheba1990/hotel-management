package by.nikita.models;

import by.nikita.models.enums.RoomStatus;

import javax.persistence.*;

@Entity
@Table(name = "room_table")
public class Room extends AbstractIdAwareEntity {

    @Column(name = "room_number")
    private Integer roomNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_category_id", referencedColumnName = "id")
    private RoomCategory roomCategory;

    @OneToOne
    @JoinColumn(name = "room_details_id", referencedColumnName = "id")
    private RoomDetails roomDetails;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "room_status", nullable = false)
    private RoomStatus roomStatus;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY)
    private Order order;

    public Room() {
    }

    public Room(Integer roomNumber,
                RoomCategory roomCategory,
                RoomDetails roomDetails,
                RoomStatus roomStatus,
                Order order) {
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.roomDetails = roomDetails;
        this.roomStatus = roomStatus;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

