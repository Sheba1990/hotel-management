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
        return roomDto;
    }

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomCategory = room.getRoomCategory().getCategoryName();
        this.roomStatus = room.getRoomStatus();
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
}
