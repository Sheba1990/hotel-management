package by.nikita.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_details_table")
public class RoomDetails extends AbstractIdAwareEntity {

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "amount_of_rooms")
    private Integer amountOfRooms;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "has_see_view")
    private boolean hasSeaView;

    @Column(name = "has_bath")
    private boolean hasBath;

    @Column(name = "has_babybed")
    private boolean hasBabyBed;

    @Column(name = "has_wifi")
    private boolean hasWifi;

    @Column(name = "has_breakfast")
    private boolean hasBreakfast;

    @OneToMany(mappedBy = "roomDetails", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomDetails() {
    }

    public RoomDetails(
            Double pricePerNight,
            Integer floor,
            Integer amountOfRooms,
            Integer capacity,
            String description,
            String picture,
            boolean hasSeaView,
            boolean hasBath,
            boolean hasBabyBed,
            boolean hasWifi,
            boolean hasBreakfast,
            List<Room> rooms) {
        this.pricePerNight = pricePerNight;
        this.floor = floor;
        this.amountOfRooms = amountOfRooms;
        this.capacity = capacity;
        this.description = description;
        this.picture = picture;
        this.hasSeaView = hasSeaView;
        this.hasBath = hasBath;
        this.hasBabyBed = hasBabyBed;
        this.hasWifi = hasWifi;
        this.hasBreakfast = hasBreakfast;
        this.rooms = rooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isHasSeaView() {
        return hasSeaView;
    }

    public void setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
    }

    public boolean isHasBath() {
        return hasBath;
    }

    public void setHasBath(boolean hasBath) {
        this.hasBath = hasBath;
    }

    public boolean isHasBabyBed() {
        return hasBabyBed;
    }

    public void setHasBabyBed(boolean hasBabyBed) {
        this.hasBabyBed = hasBabyBed;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}

