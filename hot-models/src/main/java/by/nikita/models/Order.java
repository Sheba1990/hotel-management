package by.nikita.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_table")
public class Order extends AbstractIdAwareEntity {

    @Column(name = "order_number", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderNumber;

    @Column(name = "room_category")
    private String roomCategory;

    @Column(name = "amount_of_guests")
    private Integer amountOfGuests;

    @Column(name = "date_of_check_in", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCheckIn;

    @Column(name = "date_of_check_out", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCheckOut;

    @Column(name = "approved")
    private boolean approved;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Order() {
    }

    public Order(Integer orderNumber,
                 String roomCategory,
                 Integer amountOfGuests,
                 LocalDate dateOfCheckIn,
                 LocalDate dateOfCheckOut,
                 boolean approved,
                 User user,
                 Room room) {
        this.orderNumber = orderNumber;
        this.roomCategory = roomCategory;
        this.amountOfGuests = amountOfGuests;
        this.dateOfCheckIn = dateOfCheckIn;
        this.dateOfCheckOut = dateOfCheckOut;
        this.approved = approved;
        this.user = user;
        this.room = room;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getAmountOfGuests() {
        return amountOfGuests;
    }

    public void setAmountOfGuests(Integer amountOfGuests) {
        this.amountOfGuests = amountOfGuests;
    }

    public LocalDate getDateOfCheckIn() {
        return dateOfCheckIn;
    }

    public void setDateOfCheckIn(LocalDate dateOfCheckIn) {
        this.dateOfCheckIn = dateOfCheckIn;
    }

    public LocalDate getDateOfCheckOut() {
        return dateOfCheckOut;
    }

    public void setDateOfCheckOut(LocalDate dateOfCheckOut) {
        this.dateOfCheckOut = dateOfCheckOut;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

