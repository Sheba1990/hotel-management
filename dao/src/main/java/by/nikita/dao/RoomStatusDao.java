package by.nikita.dao;

import by.nikita.dao.api.IRoomStatusDao;
import by.nikita.models.RoomStatus;

public class RoomStatusDao extends AGenericDao<RoomStatus> implements IRoomStatusDao {
    public RoomStatusDao() {
        super(RoomStatus.class);
    }
}
