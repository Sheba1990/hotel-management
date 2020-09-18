package by.nikita.models;

import javax.persistence.*;

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

    @Column(name = "see_view")
    private boolean hasSeaView;

    @Column(name = "bath")
    private boolean hasBath;

    @Column(name = "babybed")
    private boolean hasBabyBed;

    @Column(name = "wifi")
    private boolean hasWifi;

    @Column(name = "breakfast")
    private boolean hasBreakfast;

    @Column(name = "file_name")
    private String fileName;

    @OneToOne(mappedBy = "roomDetails", fetch = FetchType.LAZY)
    private Room room;

    public RoomDetails() {
    }

    public RoomDetails(
            Double pricePerNight,
            Integer floor,
            Integer amountOfRooms,
            Integer capacity,
            boolean hasSeaView,
            boolean hasBath,
            boolean hasBabyBed,
            boolean hasWifi,
            boolean hasBreakfast,
            String fileName,
            Room room) {
        this.pricePerNight = pricePerNight;
        this.floor = floor;
        this.amountOfRooms = amountOfRooms;
        this.capacity = capacity;
        this.hasSeaView = hasSeaView;
        this.hasBath = hasBath;
        this.hasBabyBed = hasBabyBed;
        this.hasWifi = hasWifi;
        this.hasBreakfast = hasBreakfast;
        this.fileName = fileName;
        this.room = room;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

