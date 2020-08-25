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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
    public RoomDto addRoom(RoomDto roomDto,
                           RoomCategoryDto roomCategoryDto,
                           RoomDetailsDto roomDetailsDto) {

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryName(roomCategoryDto.getCategoryName());
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("deluxe")) {
            roomCategory.setDescription("Deluxe room 20 – 25 m² for two to four persons with en suite shower/WC, hair dryer, balcony, double bed and bunk bed, cable TV, radio, safe and free WiFi");
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("business")) {
            roomCategory.setDescription("Business double room 20 – 25 m² for two persons with en suite bath/shower/WC, hair dryer, balcony, double bed, cable TV, radio, safe and free WiFi");
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("standard")) {
            roomCategory.setDescription("Standard room 30 m² for two to four persons with en suite bath/shower/WC, hair dryer, balcony, double bed and bunk bed or sofa bed, cable TV, radio, safe and free WiFi");
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("econom")) {
            roomCategory.setDescription("Economy single room 11 m² for one person with en suite shower/WC, hair dryer, cable TV, radio, safe and free WiFi");
        }
        roomCategoryDao.create(roomCategory);

        RoomDetails roomDetails = new RoomDetails();
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("deluxe")) {
            roomDetails.setHasSeaView(true);
            roomDetails.setHasBabyBed(true);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(200.00);
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("business")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(150.00);
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("standard")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(100.00);
        } else if (roomCategoryDto.getCategoryName().equalsIgnoreCase("econom")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(false);
            roomDetails.setPricePerNight(50.00);
        }
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());

        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        roomDetails.setHasWifi(true);
        roomDetailsDao.create(roomDetails);

        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomStatus(RoomStatus.VACANT);
        room.setRoomCategory(roomCategory);
        room.setRoomDetails(roomDetails);

        Room newRoom = roomDao.create(room);

        return RoomDto.entityToDto(newRoom);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return RoomDto.convertList(roomDao.getAll());
    }

    @Override
    public RoomDto getRoomById(long id) {
        return RoomDto.entityToDto(roomDao.get(id));
    }

    @Override
    public RoomDto getRoomByNumber(Integer roomNumber) {
        return RoomDto.entityToDto(roomDao.getRoomByNumber(roomNumber));
    }

    @Override
    public List<RoomDto> getRoomsByDeluxeCategory() {
        return RoomDto.convertList(roomDao.getRoomsByDeluxeCategory());
    }

    @Override
    public List<RoomDto> getRoomsByBusinessCategory() {
        return RoomDto.convertList(roomDao.getRoomsByBusinessCategory());
    }

    @Override
    public List<RoomDto> getRoomsByStandardCategory() {
        return RoomDto.convertList(roomDao.getRoomsByStandardCategory());
    }

    @Override
    public List<RoomDto> getRoomsByEconomCategory() {
        return RoomDto.convertList(roomDao.getRoomsByEconomCategory());
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
    public List<RoomDto> getRoomByAmountOfRooms(Integer amountOfRooms) {
        return RoomDto.convertList(roomDao.getRoomByAmountOfRooms(amountOfRooms));
    }

    @Override
    public void deleteRoom(long id) {
        roomDao.delete(roomDao.get(id));
    }

    @Override
    public void updateRoom(long id,
                           RoomDto roomDto,
                           RoomCategoryDto roomCategoryDto,
                           RoomDetailsDto roomDetailsDto) {

        Room room = roomDao.get(id);
        RoomCategory roomCategory = room.getRoomCategory();
        RoomDetails roomDetails = room.getRoomDetails();

        if (room.getRoomCategory() != null) {
            roomCategory.setCategoryName(roomDto.getRoomCategory());
            roomCategoryDao.update(roomCategory);
        }

        if (room.getRoomDetails() != null) {
            if (roomDto.getRoomCategory().equalsIgnoreCase("deluxe")) {
                roomDetails.setHasSeaView(true);
                roomDetails.setHasBabyBed(true);
                roomDetails.setHasBreakfast(true);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(200.00);
            } else if (roomDto.getRoomCategory().equalsIgnoreCase("business")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(true);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(150.00);
            } else if (roomDto.getRoomCategory().equalsIgnoreCase("standard")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(false);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(100.00);
            } else if (roomDto.getRoomCategory().equalsIgnoreCase("econom")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(false);
                roomDetails.setHasBath(false);
                roomDetails.setPricePerNight(50.00);
            }
            roomDetails.setFloor(roomDetailsDto.getFloor());
            roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
            roomDetails.setCapacity(roomDetailsDto.getCapacity());
            roomDetails.setHasWifi(true);
            roomDetailsDao.update(roomDetails);
        }

        if (roomDto.getRoomNumber() != null && !StringUtils.isEmpty(roomDto.getRoomNumber())) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }

        roomDao.update(room);
    }

    @Override
    public RoomDto addRoomDetailsToRoom(long roomId, RoomDetailsDto roomDetailsDto) {

        Room room = roomDao.get(roomId);

        RoomDetails roomDetails = new RoomDetails();
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        roomDetails.setHasWifi(true);
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("deluxe")) {
            roomDetails.setHasSeaView(true);
            roomDetails.setHasBabyBed(true);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(200.00);
        }
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("business")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(150.00);
        }
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("standard")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(100.00);
        }
        if (room.getRoomCategory().getCategoryName().equalsIgnoreCase("economic")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(false);
            roomDetails.setPricePerNight(50.00);
        }
        roomDetailsDao.create(roomDetails);

        room.setRoomDetails(roomDetails);

        roomDao.update(room);

        return RoomDto.entityToDto(room);
    }
}
