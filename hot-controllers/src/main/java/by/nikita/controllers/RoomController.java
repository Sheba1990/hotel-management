package by.nikita.controllers;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    IRoomService roomService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto addRoom(@RequestBody RoomDto roomDto, @RequestBody RoomCategoryDto roomCategoryDto) {
        return roomService.addRoom(roomDto, roomCategoryDto);
    }

    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(value = "/{id}")
    public RoomDto getRoomById(@PathVariable long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping(value = "/number/{roomNumber}")
    public List<RoomDto> getRoomByNumber(@PathVariable Integer roomNumber) {
        return roomService.getRoomByNumber(roomNumber);
    }

    @GetMapping(value = "/category/{roomCategory}")
    public List<RoomDto> getRoomsByCategory(@PathVariable String roomCategory) {
        return roomService.getRoomsByCategory(roomCategory);
    }

    @GetMapping(value = "/vacant")
    public List<RoomDto> getRoomsWhereStatusIsVacant() {
        return roomService.getRoomsWhereStatusIsVacant();
    }

    @GetMapping(value = "/occupied")
    public List<RoomDto> getRoomsWhereStatusIsOccupied() {
        return roomService.getRoomsWhereStatusIsOccupied();
    }

    @GetMapping(value = "/capacity/{roomCapacity}")
    public List<RoomDto> getRoomsByCapacity(@PathVariable Integer roomCapacity) {
        return roomService.getRoomsByCapacity(roomCapacity);
    }

    @GetMapping(value = "/amount_of_rooms/{amountOfRooms}")
    public List<RoomDto> getRoomByAmountOfRooms(@PathVariable Integer amountOfRooms) {
        return roomService.getRoomByAmountOfRooms(amountOfRooms);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRoom(long id) {
        roomService.deleteRoom(id);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateRoom(@PathVariable long id, @RequestBody RoomDto roomDto, @RequestBody RoomCategoryDto roomCategoryDto) {
        roomService.updateRoom(id, roomDto, roomCategoryDto);
    }

    @PutMapping(value = "/add_room_details/{roomId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto addRoomDetailsToRoom(@PathVariable long roomId, @RequestBody RoomDetailsDto roomDetailsDto) {
        return roomService.addRoomDetailsToRoom(roomId, roomDetailsDto);
    }
}
