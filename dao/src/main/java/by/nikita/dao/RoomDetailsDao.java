package by.nikita.dao;

import by.nikita.dao.api.IRoomDetailsDao;
import by.nikita.models.RoomDetails;

public class RoomDetailsDao extends AGenericDao<RoomDetails> implements IRoomDetailsDao {
    public RoomDetailsDao() {
        super(RoomDetails.class);
    }
}
