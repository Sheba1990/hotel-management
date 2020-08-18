package by.nikita.services;

import by.nikita.dao.api.IRoomCategoryDao;
import by.nikita.dto.RoomCategoryDto;
import by.nikita.models.RoomCategory;
import by.nikita.services.api.IRoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomCategoryService implements IRoomCategoryService {

    @Autowired
    IRoomCategoryDao roomCategoryDao;

    @Override
    public RoomCategoryDto addRoomCategory(RoomCategoryDto roomCategoryDto) {
        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryName(roomCategoryDto.getCategoryName());
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("deluxe")) {
            roomCategory.setDescription("Deluxe room 20 – 25 m² for two to four persons with en suite shower/WC, hair dryer, balcony, double bed and bunk bed, cable TV, radio, safe and free WiFi");
        }
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("business")) {
            roomCategory.setDescription("Business double room 20 – 25 m² for two persons with en suite bath/shower/WC, hair dryer, balcony, double bed, cable TV, radio, safe and free WiFi");
        }
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("standard")) {
            roomCategory.setDescription("Standard room 30 m² for two to four persons with en suite bath/shower/WC, hair dryer, balcony, double bed and bunk bed or sofa bed, cable TV, radio, safe and free WiFi");
        }
        if (roomCategoryDto.getCategoryName().equalsIgnoreCase("econom")) {
            roomCategory.setDescription("Economy single room 11 m² for one person with en suite shower/WC, hair dryer, cable TV, radio, safe and free WiFi");
        }
        return RoomCategoryDto.entityToDto(roomCategoryDao.create(roomCategory));
    }

    @Override
    public RoomCategoryDto getRoomCategoryById(long id) {
        return null;
    }

    @Override
    public List<RoomCategoryDto> getAllRoomCategories() {
        return null;
    }

    @Override
    public RoomCategoryDto getRoomCategoryByRoomNumber(Integer roomNumber) {
        return null;
    }

    @Override
    public void updateRoomCategory(long id, RoomCategoryDto roomCategoryDto) {

    }

    @Override
    public void deleteRoomCategory(long id) {

    }
}
