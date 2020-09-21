package by.nikita.dao.api;

import by.nikita.models.Room;
import by.nikita.models.enums.RoomStatus;

import java.util.List;

public interface IRoomDao extends IAbstractGenericDao<Room> {

    List<Room> getRoomByNumber(Integer roomNumber);

    List<Room> getRoomsByStatus(RoomStatus roomStatus);

    List<Room> getRoomsByCategory(String roomCategory);

    List<Room> getRoomsWhereStatusIsVacant();

    List<Room> getRoomsWhereStatusIsOccupied();

    List<Room> getRoomsByCapacity(Integer roomCapacity);

    List<Room> getVacantRoomsByCategory(String roomCategory);

    List<Room> getRoomByAmountOfRooms(Integer amountOfRooms);
}
