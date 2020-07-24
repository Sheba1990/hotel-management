package by.nikita.dto;

import by.nikita.models.Room;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class RoomDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer roomNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roomCategory;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roomStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double pricePerNight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer floor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer amountOfRooms;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer capacity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String picture;

    public static List<RoomDto> convertList(List<Room> roomList) {
        List<RoomDto> rooms = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getId());
            roomDto.setRoomNumber(room.getRoomNumber());
            roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
            roomDto.setRoomStatus(room.getRoomStatus().getStatusName());
            rooms.add(roomDto);
        }
        return rooms;
    }

    public static RoomDto entityToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
        roomDto.setRoomStatus(room.getRoomStatus().getStatusName());
        roomDto.setPricePerNight(room.getRoomDetails().getPricePerNight());
        roomDto.setFloor(room.getRoomDetails().getFloor());
        roomDto.setAmountOfRooms(room.getRoomDetails().getAmountOfRooms());
        roomDto.setCapacity(room.getRoomDetails().getCapacity());
        roomDto.setDescription(room.getRoomDetails().getDescription());
        roomDto.setPicture(room.getRoomDetails().getPicture());
        return roomDto;
    }

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomCategory = room.getRoomCategory().getCategoryName();
        this.roomStatus = room.getRoomStatus().getStatusName();
        this.pricePerNight = room.getRoomDetails().getPricePerNight();
        this.floor = room.getRoomDetails().getFloor();
        this.amountOfRooms = room.getRoomDetails().getAmountOfRooms();
        this.capacity = room.getRoomDetails().getCapacity();
        this.description = room.getRoomDetails().getDescription();
        this.picture = room.getRoomDetails().getPicture();
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
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
}
