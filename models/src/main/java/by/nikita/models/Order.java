package by.nikita.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking_table")
public class Order extends AEntity {

    @Column(name = "number")
    private Integer number;

    @Column(name = "amount_of_guests")
    private Integer amountOfGuests;

    @Column(name = "date_of_check_in")
    private LocalDate dateOfCheckIn;

    @Column(name = "date_of_check_out")
    private LocalDate dateOfCheckOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Order() {
    }

    public Order(Integer number, Integer amountOfGuests, LocalDate dateOfCheckIn, LocalDate dateOfCheckOut, User user, Room room) {
        this.number = number;
        this.amountOfGuests = amountOfGuests;
        this.dateOfCheckIn = dateOfCheckIn;
        this.dateOfCheckOut = dateOfCheckOut;
        this.user = user;
        this.room = room;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

