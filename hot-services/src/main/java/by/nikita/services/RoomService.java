package by.nikita.services;

import by.nikita.dao.api.IRoomCategoryDao;
import by.nikita.dao.api.IRoomDao;
import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.models.Room;
import by.nikita.models.RoomCategory;
import by.nikita.models.enums.RoomStatus;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class RoomService implements IRoomService {

    @Autowired
    IRoomDao roomDao;

    @Autowired
    IRoomCategoryDao roomCategoryDao;

    @Override
    public RoomDto addRoom(RoomDto roomDto, RoomCategoryDto roomCategoryDto) {

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryName(roomCategoryDto.getCategoryName());
        roomCategoryDao.create(roomCategory);

        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomStatus(RoomStatus.FREE);
        room.setRoomCategory(roomCategory);
        return RoomDto.entityToDto(roomDao.create(room));
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return RoomDto.convertList(roomDao.getAll());
    }

    @Override
    public List<RoomDto> getRoomByNumber(Integer roomNumber) {
        return RoomDto.convertList(roomDao.getRoomByNumber(roomNumber));
    }

    @Override
    public List<RoomDto> getRoomsByCategory(String roomCategory) {
        return RoomDto.convertList(roomDao.getRoomsByCategory(roomCategory));
    }

    @Override
    public List<RoomDto> getRoomsByStatus(String roomStatus) {
        return RoomDto.convertList(roomDao.getRoomsByStatus(roomStatus));
    }

    @Override
    public List<RoomDto> getRoomsByCapacity(Integer roomCapacity) {
        return RoomDto.convertList(roomDao.getRoomsByCapacity(roomCapacity));
    }

    @Override
    public List<RoomDto> getRoomByAmountOfRoom(Integer amountOfRooms) {
        return RoomDto.convertList(roomDao.getRoomByAmountOfRoom(amountOfRooms));
    }

    @Override
    public void deleteRoom(long id) {
        roomDao.delete(roomDao.get(id));
    }

    @Override
    public void updateRoom(long id, RoomDto roomDto) {
        Room room = roomDao.get(id);
        if (roomDto.getRoomNumber() != null && !StringUtils.isEmpty(roomDto.getRoomNumber())) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }
        roomDao.update(room);
    }

    @Override
    public RoomDto addRoomDetailsToRoom(long roomId, RoomDetailsDto roomDetailsDto) {
        return null;
    }
}
