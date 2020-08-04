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


    public static List<RoomDto> convertList(List<Room> roomList) {
        List<RoomDto> rooms = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getId());
            roomDto.setRoomNumber(room.getRoomNumber());
            roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
            roomDto.setRoomStatus(room.getRoomStatus().toString());
            rooms.add(roomDto);
        }
        return rooms;
    }

    public static RoomDto entityToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
        roomDto.setRoomStatus(room.getRoomStatus().toString());
        return roomDto;
    }

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomCategory = room.getRoomCategory().getCategoryName();
        this.roomStatus = room.getRoomStatus().toString();
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

}
