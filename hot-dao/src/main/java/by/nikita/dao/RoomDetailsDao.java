package by.nikita.dao;

import by.nikita.dao.api.IRoomDetailsDao;
import by.nikita.models.RoomDetails;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDetailsDao extends AbstractGenericDao<RoomDetails> implements IRoomDetailsDao {
    public RoomDetailsDao() {
        super(RoomDetails.class);
    }
}
