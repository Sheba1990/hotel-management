package by.nikita.controllers;

import by.nikita.dto.RoomDetailsDto;
import by.nikita.services.api.IRoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room_details")
public class RoomDetailsController {

    @Autowired
    IRoomDetailsService roomDetailsService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    RoomDetailsDto addRoomDetails(@RequestBody RoomDetailsDto roomDetailsDto) {
        return roomDetailsService.addRoomDetails(roomDetailsDto);
    }

    @GetMapping
    List<RoomDetailsDto> getAllRoomDetails() {
        return roomDetailsService.getAllRoomDetails();
    }

    @GetMapping(value = "/{id}")
    RoomDetailsDto getRoomDetailsById(@PathVariable long id) {
        return roomDetailsService.getRoomDetailsById(id);
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    RoomDetailsDto getRoomDetailsByRoomNumber(@PathVariable Integer roomNumber) {
        return roomDetailsService.getRoomDetailsByRoomNumber(roomNumber);
    }

    @DeleteMapping(value = "/{id}")
    void deleteRoomDetails(@PathVariable long id) {
        roomDetailsService.deleteRoomDetails(id);
    }

    @PutMapping(value = "/{id}")
    void updateRoomDetails(@PathVariable long id, @RequestBody RoomDetailsDto roomDetailsDto) {
        roomDetailsService.updateRoomDetails(id, roomDetailsDto);
    }
}
