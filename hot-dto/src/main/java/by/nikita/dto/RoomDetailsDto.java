package by.nikita.dto;

import by.nikita.models.RoomDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsDto extends AbstractIdAwareDto {

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasSeaView;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasBath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasBabyBed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasWifi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasBreakfast;


    public static List<RoomDetailsDto> convertList(List<RoomDetails> roomDetailsList) {
        List<RoomDetailsDto> roomDetailss = new ArrayList<>();
        for (RoomDetails roomDetails : roomDetailsList) {
            RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
            roomDetailsDto.setId(roomDetails.getId());
            roomDetailsDto.setRoomNumber(roomDetails.getRoom().getRoomNumber());
            roomDetailsDto.setRoomCategory(roomDetails.getRoom().getRoomCategory().getCategoryName());
            roomDetailsDto.setRoomStatus(roomDetails.getRoom().getRoomStatus().toString());
            roomDetailsDto.setPricePerNight(roomDetails.getPricePerNight());
            roomDetailsDto.setFloor(roomDetails.getFloor());
            roomDetailsDto.setAmountOfRooms(roomDetails.getAmountOfRooms());
            roomDetailsDto.setCapacity(roomDetails.getCapacity());
            roomDetailss.add(roomDetailsDto);
        }
        return roomDetailss;
    }

    public static RoomDetailsDto entityToDto(RoomDetails roomDetails) {
        RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
        roomDetailsDto.setId(roomDetails.getId());
        roomDetailsDto.setRoomNumber(roomDetails.getRoom().getRoomNumber());
        roomDetailsDto.setRoomCategory(roomDetails.getRoom().getRoomCategory().getCategoryName());
        roomDetailsDto.setRoomStatus(roomDetails.getRoom().getRoomStatus().toString());
        roomDetailsDto.setPricePerNight(roomDetails.getPricePerNight());
        roomDetailsDto.setFloor(roomDetails.getFloor());
        roomDetailsDto.setAmountOfRooms(roomDetails.getAmountOfRooms());
        roomDetailsDto.setCapacity(roomDetails.getCapacity());
        roomDetailsDto.setDescription(roomDetails.getDescription());
        roomDetailsDto.setPicture(roomDetails.getPicture());
        roomDetailsDto.setHasSeaView(roomDetails.isHasSeaView());
        roomDetailsDto.setHasBath(roomDetails.isHasBath());
        roomDetailsDto.setHasBabyBed(roomDetails.isHasBabyBed());
        roomDetailsDto.setHasWifi(roomDetails.isHasWifi());
        roomDetailsDto.setHasBreakfast(roomDetails.isHasBreakfast());
        return roomDetailsDto;
    }


    public RoomDetailsDto() {
    }

    public RoomDetailsDto(RoomDetails roomDetails) {
        this.roomNumber = roomDetails.getRoom().getRoomNumber();
        this.roomCategory = roomDetails.getRoom().getRoomCategory().getCategoryName();
        this.roomStatus = roomDetails.getRoom().getRoomStatus().toString();
        this.pricePerNight = roomDetails.getPricePerNight();
        this.floor = roomDetails.getFloor();
        this.amountOfRooms = roomDetails.getAmountOfRooms();
        this.capacity = roomDetails.getCapacity();
        this.description = roomDetails.getDescription();
        this.picture = roomDetails.getPicture();
        this.hasSeaView = roomDetails.isHasSeaView();
        this.hasBath = roomDetails.isHasBath();
        this.hasBabyBed = roomDetails.isHasBabyBed();
        this.hasWifi = roomDetails.isHasWifi();
        this.hasBreakfast = roomDetails.isHasBreakfast();
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
