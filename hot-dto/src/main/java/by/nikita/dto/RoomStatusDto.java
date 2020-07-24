package by.nikita.dto;

import by.nikita.models.Room;
import by.nikita.models.RoomStatus;

import java.util.ArrayList;
import java.util.List;

public class RoomStatusDto extends AbstractIdAwareDto {

    private String statusName;

    private List<RoomDto> rooms;

    public static List<RoomStatusDto> convertList(List<RoomStatus> roomStatusList) {
        List<RoomStatusDto> roomStatuses = new ArrayList<>();
        List<RoomDto> rooms = new ArrayList<>();
        for (RoomStatus roomStatus : roomStatusList) {
            RoomStatusDto roomStatusDto = new RoomStatusDto();
            roomStatusDto.setId(roomStatus.getId());
            roomStatusDto.setStatusName(roomStatus.getStatusName());
            for (Room room : roomStatus.getRooms()) {
                RoomDto roomDto = new RoomDto();
                if (roomStatus.getRooms() != null) {
                    roomDto.setId(room.getId());
                    roomDto.setRoomNumber(room.getRoomNumber());
                    roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
                    rooms.add(roomDto);
                } else {
                    roomStatusDto.setRooms(null);
                }
            }
            roomStatusDto.setRooms(rooms);
            roomStatuses.add(roomStatusDto);
        }
        return roomStatuses;
    }

    public static RoomStatusDto entityToDto(RoomStatus roomStatus) {
        RoomStatusDto roomStatusDto = new RoomStatusDto();
        List<RoomDto> rooms = new ArrayList<>();
        roomStatusDto.setId(roomStatus.getId());
        roomStatusDto.setStatusName(roomStatus.getStatusName());
        for (Room room : roomStatus.getRooms()) {
            RoomDto roomDto = new RoomDto();
            if (roomStatus.getRooms() != null) {
                roomDto.setId(room.getId());
                roomDto.setRoomNumber(room.getRoomNumber());
                roomDto.setRoomCategory(room.getRoomCategory().getCategoryName());
                rooms.add(roomDto);
            } else {
                roomStatusDto.setRooms(null);
            }
        }
        roomStatusDto.setRooms(rooms);
        return roomStatusDto;
    }

    public RoomStatusDto() {
    }

    public RoomStatusDto(RoomStatus roomStatus) {
        this.id = roomStatus.getId();
        this.statusName = roomStatus.getStatusName();
        this.rooms = RoomDto.convertList(roomStatus.getRooms());
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
}
