package by.nikita.services.api;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.models.enums.RoomStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IRoomService {

    RoomDto addRoom(RoomDto roomDto,
                    RoomCategoryDto roomCategoryDto,
                    RoomDetailsDto roomDetailsDto,
                    MultipartFile file) throws IOException;

    List<RoomDto> getAllRooms();

    RoomDto getRoomById(long id);

    List<RoomDto> getRoomByNumber(Integer roomNumber);

    List<RoomDto> getRoomsByCategory(String roomCategory);

    List<RoomDto> getRoomsByStatus(RoomStatus roomStatus);

    List<RoomDto> getRoomsWhereStatusIsVacant();

    List<RoomDto> getRoomsWhereStatusIsOccupied();

    List<RoomDto> getRoomsByCapacity(Integer roomCapacity);

    List<RoomDto> getRoomByAmountOfRooms(Integer amountOfRooms);

    List<RoomDto> getRoomsSuitableByOrder(long orderId);

    void deleteRoom(long id);

    void updateRoom(long id,
                    RoomDto roomDto,
                    RoomCategoryDto roomCategoryDto,
                    RoomDetailsDto roomDetailsDto,
                    MultipartFile file) throws IOException;

}
