package by.nikita.dao;

import by.nikita.dao.api.IRoomStatusDao;
import by.nikita.models.RoomStatus;
import org.springframework.stereotype.Repository;

@Repository
public class RoomStatusDao extends AbstractGenericDao<RoomStatus> implements IRoomStatusDao {
    public RoomStatusDao() {
        super(RoomStatus.class);
    }
}
