package by.nikita.dao.api;

import by.nikita.models.RoomCategory;

public interface IRoomCategoryDao extends IAbstractGenericDao<RoomCategory> {

    RoomCategory getRoomCategoryByRoomNumber(Integer roomNumber);
}
