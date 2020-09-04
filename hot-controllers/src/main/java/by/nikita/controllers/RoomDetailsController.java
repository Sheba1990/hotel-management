package by.nikita.controllers;

import by.nikita.dto.RoomDetailsDto;
import by.nikita.services.api.IRoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping(value = "/room_number/{roomNumber}")
    RoomDetailsDto getRoomDetailsByRoomNumber(@PathVariable Integer roomNumber) {
        return roomDetailsService.getRoomDetailsByRoomNumber(roomNumber);
    }

    @DeleteMapping(value = "/{id}")
    void deleteRoomDetails(@PathVariable long id) {
        roomDetailsService.deleteRoomDetails(id);
    }

    @GetMapping(value = "/edit_room/{id}")
    public ModelAndView showRoomEditForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/rooms/room_edit");
        RoomDetailsDto roomDetailsDto = roomDetailsService.getRoomDetailsById(id);
        modelAndView.addObject("room", roomDetailsDto);
        return modelAndView;
    }


    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void editRoomDetails(@PathVariable("id") long id,
                         RoomDetailsDto roomDetailsDto) {
        roomDetailsService.updateRoomDetails(id, roomDetailsDto);
    }
}
