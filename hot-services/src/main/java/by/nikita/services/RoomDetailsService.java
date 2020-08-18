package by.nikita.services;

import by.nikita.dao.api.IRoomDetailsDao;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.models.RoomDetails;
import by.nikita.services.api.IRoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomDetailsService implements IRoomDetailsService {

    @Autowired
    IRoomDetailsDao roomDetailsDao;

    @Override
    public RoomDetailsDto addRoomDetails(RoomDetailsDto roomDetailsDto) {
        RoomDetails roomDetails = new RoomDetails();
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("deluxe")) {
            roomDetails.setHasSeaView(true);
            roomDetails.setHasBabyBed(true);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(200.00);
        }
        if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("business")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(true);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(150.00);
        }
        if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("standard")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(true);
            roomDetails.setPricePerNight(100.00);
        }
        if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("economic")) {
            roomDetails.setHasSeaView(false);
            roomDetails.setHasBabyBed(false);
            roomDetails.setHasBreakfast(false);
            roomDetails.setHasBath(false);
            roomDetails.setPricePerNight(50.00);
        }

        RoomDetails newRoomDetails = roomDetailsDao.create(roomDetails);

        return RoomDetailsDto.entityToDto(newRoomDetails);
    }

    @Override
    public List<RoomDetailsDto> getAllRoomDetails() {
        return RoomDetailsDto.convertList(roomDetailsDao.getAll());
    }

    @Override
    public RoomDetailsDto getRoomDetailsById(long id) {
        return RoomDetailsDto.entityToDto(roomDetailsDao.get(id));
    }

    @Override
    public RoomDetailsDto getRoomDetailsByRoomNumber(Integer roomNumber) {
        return RoomDetailsDto.entityToDto(roomDetailsDao.getRoomDetailsByRoomNumber(roomNumber));
    }

    @Override
    public void deleteRoomDetails(long id) {
        roomDetailsDao.delete(roomDetailsDao.get(id));
    }

    @Override
    public void updateRoomDetails(long id, RoomDetailsDto roomDetailsDto) {
        RoomDetails roomDetails = roomDetailsDao.get(id);
        if (roomDetailsDto.getFloor() != null) {
            roomDetails.setFloor(roomDetailsDto.getFloor());
        }
        if (roomDetailsDto.getAmountOfRooms() != null) {
            roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        }
        if (roomDetailsDto.getCapacity() != null) {
            roomDetails.setCapacity(roomDetailsDto.getCapacity());
        }
        if (roomDetails.getRoom().getRoomCategory() != null) {
            if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("deluxe")) {
                roomDetails.setHasSeaView(true);
                roomDetails.setHasBabyBed(true);
                roomDetails.setHasBreakfast(true);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(200.00);
            }
            if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("business")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(true);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(150.00);
            }
            if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("standard")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(false);
                roomDetails.setHasBath(true);
                roomDetails.setPricePerNight(100.00);
            }
            if (roomDetails.getRoom().getRoomCategory().getCategoryName().equalsIgnoreCase("econom")) {
                roomDetails.setHasSeaView(false);
                roomDetails.setHasBabyBed(false);
                roomDetails.setHasBreakfast(false);
                roomDetails.setHasBath(false);
                roomDetails.setPricePerNight(50.00);
            }
        }
        roomDetailsDao.update(roomDetails);
    }
}
