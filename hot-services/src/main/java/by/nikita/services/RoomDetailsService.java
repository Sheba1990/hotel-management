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
    private IRoomDetailsDao roomDetailsDao;

    @Override
    public RoomDetailsDto addRoomDetails(RoomDetailsDto roomDetailsDto) {
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
        roomDetails.setHasSeaView(roomDetailsDto.isHasSeaView());
        roomDetails.setHasBabyBed(roomDetailsDto.isHasBabyBed());
        roomDetails.setHasBreakfast(roomDetailsDto.isHasBreakfast());
        roomDetails.setHasBath(roomDetailsDto.isHasBath());
        roomDetails.setPricePerNight(roomDetailsDto.getPricePerNight());
        roomDetails.setFloor(roomDetailsDto.getFloor());
        roomDetails.setAmountOfRooms(roomDetailsDto.getAmountOfRooms());
        roomDetails.setCapacity(roomDetailsDto.getCapacity());
        roomDetails.setHasWifi(true);
        roomDetailsDao.update(roomDetails);
    }
}
