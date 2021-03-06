package by.nikita.services.api;

import by.nikita.dto.RoomDetailsDto;

import java.util.List;

public interface IRoomDetailsService {

    RoomDetailsDto addRoomDetails(RoomDetailsDto roomDetailsDto);

    List<RoomDetailsDto> getAllRoomDetails();

    RoomDetailsDto getRoomDetailsById(long id);

    RoomDetailsDto getRoomDetailsByRoomNumber(Integer roomNumber);

    void deleteRoomDetails(long id);

    void updateRoomDetails(long id,
                           RoomDetailsDto roomDetailsDto);
}
