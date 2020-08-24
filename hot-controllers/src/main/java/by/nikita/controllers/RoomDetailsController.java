package by.nikita.controllers;

import by.nikita.dto.RoomDetailsDto;
import by.nikita.services.api.IRoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/room_details")
public class RoomDetailsController {

    @Autowired
    IRoomDetailsService roomDetailsService;

    @GetMapping(value = "/new")
    public ModelAndView showNewRoomDetailsForm() {
        RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("room_details", roomDetailsDto);
        modelAndView.setViewName("/views/room_details/new_room_details");
        return modelAndView;
    }

    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addRoomDetails(RoomDetailsDto roomDetailsDto) {
        ModelAndView modelAndView = new ModelAndView();
        roomDetailsService.addRoomDetails(roomDetailsDto);
        modelAndView.setViewName("redirect:/rooms");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllRoomDetails() {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDetailsDto> rooms = roomDetailsService.getAllRoomDetails();
        modelAndView.setViewName("/views/rooms/all_rooms");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping(value = "/get/{id}")
    public ModelAndView getRoomDetailsById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        RoomDetailsDto roomDetailsDto = roomDetailsService.getRoomDetailsById(id);
        modelAndView.setViewName("/views/rooms/room");
        modelAndView.addObject("roomDetails", roomDetailsDto);
        return modelAndView;
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
