package by.nikita.controllers;

import by.nikita.dto.RoomCategoryDto;
import by.nikita.dto.RoomDetailsDto;
import by.nikita.dto.RoomDto;
import by.nikita.models.enums.RoomStatus;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Value("${room.upload.path}")
    private String roomUploadPath;

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

    @PostMapping(value = "/save")
    public ModelAndView addRoom(RoomDto roomDto,
                                RoomCategoryDto roomCategoryDto,
                                RoomDetailsDto roomDetailsDto,
                                @RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        roomService.addRoom(roomDto, roomCategoryDto, roomDetailsDto, file);
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
    public ModelAndView getRoomsByNumber(@RequestParam("roomNumber") Integer roomNumber) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomByNumber(roomNumber);
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("number", roomNumber);
        modelAndView.setViewName("/views/rooms/all_rooms");
        return modelAndView;
    }

    @GetMapping(value = "/category/{roomCategory}")
    public ModelAndView getRoomsByCategory(@RequestParam String roomCategory) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByCategory(roomCategory);
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/status/{roomStatus}")
    public ModelAndView getRoomsByStatus(@RequestParam RoomStatus roomStatus) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDto> rooms = roomService.getRoomsByStatus(roomStatus);
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


    @GetMapping(value = "/edit/{id}")
    public ModelAndView showRoomEditForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/rooms/room_edit");
        RoomDto roomDto = roomService.getRoomById(id);
        modelAndView.addObject("room", roomDto);
        return modelAndView;
    }

    @PostMapping(value = "/save/{id}")
    public ModelAndView editRoom(@PathVariable("id") long id,
                                 RoomDto roomDto,
                                 RoomCategoryDto roomCategoryDto,
                                 RoomDetailsDto roomDetailsDto,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/rooms/get/" + id);
        roomService.updateRoom(id, roomDto, roomCategoryDto, roomDetailsDto, file);
        return modelAndView;
    }

}
