package by.nikita.services.api;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;

import java.util.List;

public interface IRoomService {

    RoomDto addRoom(RoomDto roomDto, RoomCategoryDto roomCategoryDto);

    List<RoomDto> getAllRooms();

    List<RoomDto> getRoomByNumber(Integer roomNumber);

    List<RoomDto> getRoomsByCategory(String roomCategory);

    List<RoomDto> getRoomsWhereStatusIsVacant();

    List<RoomDto> getRoomsWhereStatusIsOccupied();

    List<RoomDto> getRoomsByCapacity(Integer roomCapacity);

    List<RoomDto> getRoomByAmountOfRooms(Integer amountOfRooms);

    List<RoomDto> getRoomsSuitableByOrder(long orderId);

    void deleteRoom(long id);

    void updateRoom(long id, RoomDto roomDto);

    RoomDto addRoomDetailsToRoom(long roomId, RoomDetailsDto roomDetailsDto);
}
