package by.nikita.dao.api;

import by.nikita.models.Room;

import java.util.List;

public interface IRoomDao extends IAGenericDao<Room> {

    Room getRoomByNumber(Integer number);

    List<Room> getRoomsByCategory(String category);

    List<Room> getRoomsByStatus(String status);
}
