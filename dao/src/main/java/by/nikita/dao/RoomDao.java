package by.nikita.dao;

import by.nikita.dao.api.IRoomDao;
import by.nikita.models.Room;

import java.util.List;

public class RoomDao extends AGenericDao<Room> implements IRoomDao {

    public RoomDao() {
        super(Room.class);
    }

    @Override
    public Room getRoomByNumber(Integer number) {
        return null;
    }

    @Override
    public List<Room> getRoomsByCategory(String category) {
        return null;
    }

    @Override
    public List<Room> getRoomsByStatus(String status) {
        return null;
    }
}
