package by.nikita.dao.api;

import by.nikita.models.RoomStatus;

public interface IRoomStatusDao extends IAbstractGenericDao<RoomStatus> {

    RoomStatus getRoomStatusByRoomNumber(Integer roomNumber);
}
