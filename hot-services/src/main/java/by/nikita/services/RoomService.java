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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoomService implements IRoomService {

    @Value("${room.upload.path}")
    private String roomUploadPath;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IRoomCategoryDao roomCategoryDao;

    @Autowired
    private IRoomDetailsDao roomDetailsDao;


    public List<RoomDto> getRoomsSuitableByOrder(long orderId) {
        Order order = orderDao.get(orderId);
        List<Room> rooms = roomDao.getVacantRoomsByCategory(order.getRoomCategory());
        return RoomDto.convertList(rooms);
    }

    @Override
    public RoomDto addRoom(RoomDto roomDto,
                           RoomCategoryDto roomCategoryDto,
                           RoomDetailsDto roomDetailsDto,
                           MultipartFile file) throws IOException {

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
        roomDetails.setHasSeaView(roomDetailsDto.isHasSeaView());
        roomDetails.setHasBabyBed(roomDetailsDto.isHasBabyBed());
        roomDetails.setHasBreakfast(roomDetailsDto.isHasBreakfast());
        roomDetails.setHasBath(roomDetailsDto.isHasBath());
        roomDetails.setPricePerNight(roomDetailsDto.getPricePerNight());
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        roomDetails.setHasWifi(true);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(roomUploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();

            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(roomUploadPath + "/" + resultFileName));

            roomDetails.setFileName(resultFileName);
        }
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
    public List<RoomDto> getRoomsByStatus(RoomStatus roomStatus) {
        return RoomDto.convertList(roomDao.getRoomsByStatus(roomStatus));
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
                           RoomDetailsDto roomDetailsDto,
                           MultipartFile file) throws IOException {

        Room room = roomDao.get(id);
        RoomCategory roomCategory = room.getRoomCategory();
        RoomDetails roomDetails = room.getRoomDetails();

        if (room.getRoomCategory() != null) {
            roomCategory.setCategoryName(roomDto.getRoomCategory());
            roomCategoryDao.update(roomCategory);
        }

        if (room.getRoomDetails() != null) {
            roomDetails.setHasSeaView(roomDetailsDto.isHasSeaView());
            roomDetails.setHasBabyBed(roomDetailsDto.isHasBabyBed());
            roomDetails.setHasBreakfast(roomDetailsDto.isHasBreakfast());
            roomDetails.setHasBath(roomDetailsDto.isHasBath());
            roomDetails.setPricePerNight(roomDetailsDto.getPricePerNight());
            roomDetails.setFloor(roomDetailsDto.getFloor());
            roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
            roomDetails.setCapacity(roomDetailsDto.getCapacity());
            roomDetails.setHasWifi(true);
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(roomUploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();

                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(roomUploadPath + "/" + resultFileName));

                roomDetails.setFileName(resultFileName);
            }
            roomDetailsDao.update(roomDetails);
        }

        if (roomDto.getRoomNumber() != null && !StringUtils.isEmpty(roomDto.getRoomNumber())) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }

        roomDao.update(room);
    }

}
