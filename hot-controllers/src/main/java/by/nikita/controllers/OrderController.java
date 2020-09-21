package by.nikita.controllers;

import by.nikita.dto.OrderDto;
import by.nikita.dto.RoomDto;
import by.nikita.services.api.IOrderService;
import by.nikita.services.api.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IRoomService roomService;

    @GetMapping(value = "/new")
    public ModelAndView showNewOrderFormForUser() {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        OrderDto orderDto = new OrderDto();
        modelAndView.addObject("order", orderDto);
        modelAndView.setViewName("/views/orders/new_order");
        return modelAndView;
    }

    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addOrderByUser(OrderDto orderDto) {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        orderService.addOrderByUser(orderDto);
        modelAndView.setViewName("redirect:/orders/username/?username={username}");
        return modelAndView;
    }

    @GetMapping(value = "/username/")
    public ModelAndView getOrdersByUsernameForUser(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        List<OrderDto> orders = orderService.getOrdersByUsername(username);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/my_orders");
        return modelAndView;
    }

    @GetMapping(value = "/all")
    public ModelAndView getAllOrders() {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/get/{id}")
    public ModelAndView getOrderById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            modelAndView.addObject("username", (((User) principal).getUsername()));
        }
        OrderDto order = orderService.getOrderById(id);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("/views/orders/order");
        return modelAndView;
    }

    @GetMapping(value = "/room_category/{roomCategory}")
    public ModelAndView getOrdersByRoomCategory(@RequestParam String roomCategory) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByRoomCategory(roomCategory);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/user_first_name/{firstName}")
    public ModelAndView getOrdersByUserFirstName(@RequestParam("firstName") String firstName) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByUserFirstName(firstName);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/user_last_name/{lastName}")
    public ModelAndView getOrdersByUserLastName(@RequestParam("lastName") String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByUserLastName(lastName);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    public ModelAndView getOrderByRoomNumber(@RequestParam(value = "roomNumber",required = false,defaultValue = "") Integer roomNumber) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByRoomNumber(roomNumber);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/for_admin/{username}")
    public ModelAndView getOrdersByUsernameForAdmin(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByUsername(username);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/not_approved")
    public ModelAndView getAllNotApprovedOrders() {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> notApprovedOrders = orderService.getAllNotApprovedOrders();
        modelAndView.addObject("orders", notApprovedOrders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/approved")
    public ModelAndView getAllApprovedOrders() {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> approvedOrders = orderService.getAllApprovedOrders();
        modelAndView.addObject("orders", approvedOrders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/approve/{id}")
    public ModelAndView showApproveOrderForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/orders/approve_order");
        OrderDto orderDto = orderService.getOrderById(id);
        List<RoomDto> rooms = roomService.getRoomsSuitableByOrder(orderDto.getId());
        modelAndView.addObject("order", orderDto);
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @PostMapping(value = "/approved/{orderId}/{roomId}")
    public ModelAndView approveOrder(@PathVariable("orderId") long orderId, @PathVariable("roomId") long roomId) {
        ModelAndView modelAndView = new ModelAndView();
        orderService.approveOrderByAdmin(orderId, roomId);
        modelAndView.setViewName("redirect:/orders/all");
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEditOrderForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/orders/order_edit");
        OrderDto orderDto = orderService.getOrderById(id);
        modelAndView.addObject("order", orderDto);
        return modelAndView;
    }

    @PostMapping(value = "/save/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editOrder(@PathVariable("id") long id,
                                  OrderDto orderDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/orders/get/" + id);
        orderService.editOrder(id, orderDto);
        return modelAndView;
    }

    @PostMapping(value = "/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        orderService.deleteOrder(id);
        modelAndView.setViewName("redirect:/orders/all");
        return modelAndView;
    }

}
