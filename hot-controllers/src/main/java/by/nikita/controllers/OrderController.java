package by.nikita.controllers;

import by.nikita.dto.OrderDto;
import by.nikita.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    OrderDto addOrderByUser(OrderDto orderDto) {
        return orderService.addOrderByUser(orderDto);
    }

    @GetMapping
    List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{id}")
    OrderDto getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(value = "/number/{orderNumber}")
    OrderDto getOrderByNumber(@PathVariable Integer orderNumber) {
        return orderService.getOrderByNumber(orderNumber);
    }

    @GetMapping(value = "/room_category/{roomCategory}")
    List<OrderDto> getOrdersByRoomCategory(@PathVariable String roomCategory) {
        return orderService.getOrdersByRoomCategory(roomCategory);
    }

    @GetMapping(value = "/user_first_name/{firstName}")
    List<OrderDto> getOrdersByUserFirstName(@PathVariable String firstName) {
        return orderService.getOrdersByUserFirstName(firstName);
    }

    @GetMapping(value = "/user_last_name/{lastName}")
    List<OrderDto> getOrdersByUserLastName(@PathVariable String lastName) {
        return orderService.getOrdersByUserLastName(lastName);
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    List<OrderDto> getOrdersByRoomNumber(@PathVariable Integer roomNumber) {
        return orderService.getOrdersByRoomNumber(roomNumber);
    }

    @PutMapping(value = "/edit/{id}")
    void updateOrder(@PathVariable long id, @RequestBody OrderDto orderDto) {
        orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping(value = "/{id}")
    void deleteOrder(long id) {
        orderService.deleteOrder(id);
    }

}
