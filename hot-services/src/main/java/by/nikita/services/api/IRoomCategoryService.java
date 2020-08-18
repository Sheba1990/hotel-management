package by.nikita.services.api;

import by.nikita.dto.RoomCategoryDto;

import java.util.List;

public interface IRoomCategoryService {

    RoomCategoryDto addRoomCategory(RoomCategoryDto roomCategoryDto);

    RoomCategoryDto getRoomCategoryById(long id);

    List<RoomCategoryDto> getAllRoomCategories();

    RoomCategoryDto getRoomCategoryByRoomNumber(Integer roomNumber);

    void updateRoomCategory(long id, RoomCategoryDto roomCategoryDto);

    void deleteRoomCategory(long id);
}
