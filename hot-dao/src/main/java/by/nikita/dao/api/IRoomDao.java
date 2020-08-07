package by.nikita.dao.api;

import by.nikita.models.Room;

import java.util.List;

public interface IRoomDao extends IAbstractGenericDao<Room> {

    List<Room> getRoomByNumber(Integer roomNumber);

    List<Room> getRoomsByCategory(String roomCategory);

    List<Room> getRoomsWhereStatusIsVacant();

    List<Room> getRoomsWhereStatusIsOccupied();

    List<Room> getRoomsByCapacity(Integer roomCapacity);

    List<Room> getRoomByCategoryAndCapacity(String roomCategory, Integer capacity);

    List<Room> getRoomByAmountOfRoom(Integer amountOfRooms);
}
