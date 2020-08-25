package by.nikita.services.api;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;

import java.util.List;

public interface IRoomService {

    RoomDto addRoom(RoomDto roomDto, RoomCategoryDto roomCategoryDto, RoomDetailsDto roomDetailsDto);

    List<RoomDto> getAllRooms();

    RoomDto getRoomById(long id);

    RoomDto getRoomByNumber(Integer roomNumber);

    List<RoomDto> getRoomsByDeluxeCategory();

    List<RoomDto> getRoomsByBusinessCategory();

    List<RoomDto> getRoomsByStandardCategory();

    List<RoomDto> getRoomsByEconomCategory();

    List<RoomDto> getRoomsWhereStatusIsVacant();

    List<RoomDto> getRoomsWhereStatusIsOccupied();

    List<RoomDto> getRoomsByCapacity(Integer roomCapacity);

    List<RoomDto> getRoomByAmountOfRooms(Integer amountOfRooms);

    List<RoomDto> getRoomsSuitableByOrder(long orderId);

    void deleteRoom(long id);

    void updateRoom(long id, RoomDto roomDto, RoomCategoryDto roomCategoryDto, RoomDetailsDto roomDetailsDto);

    RoomDto addRoomDetailsToRoom(long roomId, RoomDetailsDto roomDetailsDto);
}
