package by.nikita.services;

import by.nikita.dao.api.IOrderDao;
import by.nikita.dao.api.IRoomCategoryDao;
import by.nikita.dao.api.IRoomDao;
import by.nikita.dao.api.IRoomDetailsDao;
import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.models.Order;
import by.nikita.models.Room;
import by.nikita.models.RoomCategory;
import by.nikita.models.RoomDetails;
import by.nikita.models.enums.RoomStatus;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class RoomService implements IRoomService {

    @Autowired
    IRoomDao roomDao;

    @Autowired
    IOrderDao orderDao;

    @Autowired
    IRoomCategoryDao roomCategoryDao;

    @Autowired
    IRoomDetailsDao roomDetailsDao;


    public List<RoomDto> getRoomsSuitableByOrder(long orderId) {
        Order order = orderDao.get(orderId);
        List<Room> rooms = roomDao.getRoomByCategoryAndCapacity(order.getRoomCategory(), order.getAmountOfGuests());
        return RoomDto.convertList(rooms);
    }

    @Override
    public RoomDto addRoom(RoomDto roomDto, RoomCategoryDto roomCategoryDto) {

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryName(roomCategoryDto.getCategoryName());
        roomCategoryDao.create(roomCategory);

        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomStatus(RoomStatus.VACANT);
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
    public List<RoomDto> getRoomsWhereStatusIsVacant() {
        return RoomDto.convertList(roomDao.getRoomsWhereStatusIsVacant());
    }

    @Override
    public List<RoomDto> getRoomsWhereStatusIsOccupied() {
        return RoomDto.convertList(roomDao.getRoomsWhereStatusIsOccupied());
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
        if (room.getOrder().getUser() == null) {
            room.setRoomStatus(RoomStatus.VACANT);
        } else {
            room.setRoomStatus(RoomStatus.OCCUPIED);
        }
        roomDao.update(room);
    }

    @Override
    public RoomDto addRoomDetailsToRoom(long roomId, RoomDetailsDto roomDetailsDto) {

        Room room = roomDao.get(roomId);

        RoomDetails roomDetails = new RoomDetails();
        roomDetails.setPricePerNight(roomDetailsDto.getPricePerNight());
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        roomDetails.setDescription(roomDetailsDto.getDescription());
        roomDetails.setHasWifi(true);
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("luxury")) {
            roomDetails.setHasSeaView(true);
            roomDetails.setHasBabyBed(true);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
        }
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("business")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
        }
        if(room.getRoomCategory().getCategoryName().equalsIgnoreCase("standard")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(true);
        }
        if(room.getRoomCategory().getCategoryName().equalsIgnoreCase("economic")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(false);
        }
        roomDetailsDao.create(roomDetails);

        room.setRoomDetails(roomDetails);

        roomDao.update(room);

        return RoomDto.entityToDto(room);
    }
}
