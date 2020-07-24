package by.nikita.dto;

import by.nikita.models.Room;
import by.nikita.models.RoomCategory;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class RoomCategoryDto extends AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryName;

    private List<RoomDto> rooms;

    public static List<RoomCategoryDto> convertList(List<RoomCategory> roomCategoryList) {
        List<RoomCategoryDto> roomCategories = new ArrayList<>();
        List<RoomDto> rooms = new ArrayList<>();
        for (RoomCategory roomCategory : roomCategoryList) {
            RoomCategoryDto roomCategoryDto = new RoomCategoryDto();
            roomCategoryDto.setId(roomCategory.getId());
            roomCategoryDto.setCategoryName(roomCategory.getCategoryName());
            for (Room room : roomCategory.getRooms()) {
                RoomDto roomDto = new RoomDto();
                if (roomCategory.getRooms() != null) {
                    roomDto.setId(room.getId());
                    roomDto.setRoomNumber(room.getRoomNumber());
                    roomDto.setRoomStatus(room.getRoomStatus().getStatusName());
                    rooms.add(roomDto);
                } else {
                    roomCategory.setRooms(null);
                }
            }
            roomCategoryDto.setRooms(rooms);
            roomCategories.add(roomCategoryDto);
        }
        return roomCategories;
    }

    public static RoomCategoryDto entityToDto(RoomCategory roomCategory) {
        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();
        List<RoomDto> rooms = new ArrayList<>();
        roomCategoryDto.setId(roomCategory.getId());
        roomCategoryDto.setCategoryName(roomCategory.getCategoryName());
        for (Room room : roomCategory.getRooms()) {
            RoomDto roomDto = new RoomDto();
            if (roomCategory.getRooms() != null) {
                roomDto.setId(room.getId());
                roomDto.setRoomNumber(room.getRoomNumber());
                roomDto.setRoomStatus(room.getRoomStatus().getStatusName());
                rooms.add(roomDto);
            } else {
                roomCategory.setRooms(null);
            }
        }
        roomCategoryDto.setRooms(rooms);
        return roomCategoryDto;
    }

    public RoomCategoryDto() {
    }

    public RoomCategoryDto(RoomCategory roomCategory) {
        this.categoryName = roomCategory.getCategoryName();
        this.rooms = RoomDto.convertList(roomCategory.getRooms());
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
}
