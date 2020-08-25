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

    @GetMapping(value = "/new")
    public ModelAndView showNewRoomForm() {
        ModelAndView modelAndView = new ModelAndView();
        RoomDto roomDto = new RoomDto();
        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();
        RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
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
        modelAndView.addObject("number", roomNumber);
        return modelAndView;
    }

    @GetMapping(value = "/deluxe")
    public ModelAndView getRoomsByDeluxeCategory() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByDeluxeCategory();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/business")
    public ModelAndView getRoomsByBusinessCategory() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByBusinessCategory();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/standard")
    public ModelAndView getRoomsByStandardCategory() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByStandardCategory();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/econom")
    public ModelAndView getRoomsByEconomCategory() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByEconomCategory();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/vacant")
    public ModelAndView getRoomsWhereStatusIsVacant() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsWhereStatusIsVacant();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/occupied")
    public ModelAndView getRoomsWhereStatusIsOccupied() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsWhereStatusIsOccupied();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/capacity/{roomCapacity}")
    public ModelAndView getRoomsByCapacity(@PathVariable Integer roomCapacity) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByCapacity(roomCapacity);
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/amount_of_rooms/{amountOfRooms}")
    public ModelAndView getRoomByAmountOfRooms(@PathVariable Integer amountOfRooms) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomByAmountOfRooms(amountOfRooms);
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @PostMapping(value = "/delete/{id}")
    public ModelAndView deleteRoom(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        roomService.deleteRoom(id);
        modelAndView.setViewName("redirect:/rooms");
        return modelAndView;
    }


    @GetMapping(value = "/edit_room/{id}")
    public ModelAndView showRoomEditForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/rooms/room_edit");
        RoomDto roomDto = roomService.getRoomById(id);
        modelAndView.addObject("room", roomDto);
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editRoom(@PathVariable("id") long id,
                                 RoomDto roomDto,
                                 RoomCategoryDto roomCategoryDto,
                                 RoomDetailsDto roomDetailsDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/rooms/get/" + id);
        roomService.updateRoom(id, roomDto, roomCategoryDto, roomDetailsDto);
        return modelAndView;
    }

    @PutMapping(value = "/add_room_details/{roomId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto addRoomDetailsToRoom(@PathVariable long roomId,
                                        @RequestBody RoomDetailsDto roomDetailsDto) {
        return roomService.addRoomDetailsToRoom(roomId, roomDetailsDto);
    }
}
