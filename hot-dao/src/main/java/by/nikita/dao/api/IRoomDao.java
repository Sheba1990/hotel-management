package by.nikita.dao.api;

import by.nikita.models.Room;

import java.util.List;

public interface IRoomDao extends IAbstractGenericDao<Room> {

    Room getRoomByNumber(Integer roomNumber);

    List<Room> getRoomsByDeluxeCategory();

    List<Room> getRoomsByBusinessCategory();

    List<Room> getRoomsByStandardCategory();

    List<Room> getRoomsByEconomCategory();

    List<Room> getRoomsWhereStatusIsVacant();

    List<Room> getRoomsWhereStatusIsOccupied();

    List<Room> getRoomsByCapacity(Integer roomCapacity);

    List<Room> getRoomByCategoryAndCapacity(String roomCategory, Integer capacity);

    List<Room> getRoomByAmountOfRooms(Integer amountOfRooms);
}
