package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_table")
public class Room extends AEntity {

    @Column(name = "number")
    private Integer number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id")
    private RoomDetails details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private RoomStatus roomStatus;

    @OneToMany(mappedBy = "room")
    private List<Order> bookings;

    public Room() {
    }

    public Room(Integer number, Category category, RoomDetails details, RoomStatus roomStatus, List<Order> bookings) {
        this.number = number;
        this.category = category;
        this.details = details;
        this.roomStatus = roomStatus;
        this.bookings = bookings;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public RoomDetails getDetails() {
        return details;
    }

    public void setDetails(RoomDetails details) {
        this.details = details;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<Order> getBookings() {
        return bookings;
    }

    public void setBookings(List<Order> bookings) {
        this.bookings = bookings;
    }
}

