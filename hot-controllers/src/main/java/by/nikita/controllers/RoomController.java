package by.nikita.controllers;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    IRoomService roomService;

    @GetMapping("/new")
    public ModelAndView showNewRoomForm() {
        RoomDto roomDto = new RoomDto();
        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();
        RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("room", roomDto);
        modelAndView.addObject("category", roomCategoryDto);
        modelAndView.addObject("details", roomDetailsDto);
        modelAndView.setViewName("/views/rooms/new_room");
        return modelAndView;
    }

    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addRoom(RoomDto roomDto,
                                RoomCategoryDto roomCategoryDto,
                                RoomDetailsDto roomDetailsDto) {
        ModelAndView modelAndView = new ModelAndView();
        roomService.addRoom(roomDto, roomCategoryDto, roomDetailsDto);
        modelAndView.setViewName("redirect:/rooms");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllRooms() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getAllRooms();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/get/{id}")
    public ModelAndView getRoomById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        RoomDto room = roomService.getRoomById(id);
        modelAndView.setViewName("/views/rooms/room");
        modelAndView.addObject("room", room);
        return modelAndView;
    }

    @GetMapping(value = "/number/{roomNumber}")
    public ModelAndView getRoomByNumber(@PathVariable Integer roomNumber) {
        ModelAndView modelAndView = new ModelAndView();
        RoomDto room = roomService.getRoomByNumber(roomNumber);
        modelAndView.setViewName("/views/rooms/room");
        modelAndView.addObject("room", room);
        return modelAndView;
    }

    @GetMapping(value = "/deluxe")
    public List<RoomDto> getRoomsByDeluxeCategory() {
        return roomService.getRoomsByDeluxeCategory();
    }

    @GetMapping(value = "/business")
    public List<RoomDto> getRoomsByBusinessCategory() {
        return roomService.getRoomsByBusinessCategory();
    }

    @GetMapping(value = "/standard")
    public List<RoomDto> getRoomsByStandardCategory() {
        return roomService.getRoomsByStandardCategory();
    }

    @GetMapping(value = "/econom")
    public List<RoomDto> getRoomsByEconomCategory() {
        return roomService.getRoomsByEconomCategory();
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
