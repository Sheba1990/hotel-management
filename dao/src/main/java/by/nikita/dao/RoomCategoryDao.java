package by.nikita.dao;

import by.nikita.dao.api.IRoomCategoryDao;
import by.nikita.models.RoomCategory;
import org.springframework.stereotype.Repository;

@Repository
public class RoomCategoryDao extends AbstractGenericDao<RoomCategory> implements IRoomCategoryDao {

    public RoomCategoryDao() {
        super(RoomCategory.class);
    }
}
