package by.nikita.dto;

import by.nikita.models.Room;
import by.nikita.models.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto extends AbstractIdAwareDto {

    private Integer roomNumber;

    private String roomCategory;

    private RoomStatus roomStatus;

    private Double pricePerNight;

    private Integer floor;

    private Integer amountOfRooms;

    private Integer capacity;

    private String fileName;

    private boolean hasSeaView;

    private boolean hasBath;

    private boolean hasBabyBed;

    private boolean hasWifi;

    private boolean hasBreakfast;


    public static List<RoomDto> convertList(List<Room> roomList) {
        List<RoomDto> rooms = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getId());
            roomDto.setRoomNumber(room.getRoomNumber());
            roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
            roomDto.setRoomStatus(room.getRoomStatus());
            rooms.add(roomDto);
        }
        return rooms;
    }

    public static RoomDto entityToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
        roomDto.setRoomStatus(room.getRoomStatus());
        roomDto.setPricePerNight(room.getRoomDetails().getPricePerNight());
        roomDto.setFloor(room.getRoomDetails().getFloor());
        roomDto.setAmountOfRooms(room.getRoomDetails().getAmountOfRooms());
        roomDto.setCapacity(room.getRoomDetails().getCapacity());
        roomDto.setFileName(room.getRoomDetails().getFileName());
        roomDto.setHasSeaView(room.getRoomDetails().isHasSeaView());
        roomDto.setHasBath(room.getRoomDetails().isHasBath());
        roomDto.setHasBabyBed(room.getRoomDetails().isHasBabyBed());
        roomDto.setHasWifi(room.getRoomDetails().isHasWifi());
        roomDto.setHasBreakfast(room.getRoomDetails().isHasBreakfast());
        return roomDto;
    }

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomCategory = room.getRoomCategory().getCategoryName();
        this.roomStatus = room.getRoomStatus();
        this.pricePerNight = room.getRoomDetails().getPricePerNight();
        this.floor = room.getRoomDetails().getFloor();
        this.amountOfRooms = room.getRoomDetails().getAmountOfRooms();
        this.capacity = room.getRoomDetails().getCapacity();
        this.fileName = room.getRoomDetails().getFileName();
        this.hasSeaView = room.getRoomDetails().isHasSeaView();
        this.hasBath = room.getRoomDetails().isHasBath();
        this.hasBabyBed = room.getRoomDetails().isHasBabyBed();
        this.hasWifi = room.getRoomDetails().isHasWifi();
        this.hasBreakfast = room.getRoomDetails().isHasBreakfast();
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

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
