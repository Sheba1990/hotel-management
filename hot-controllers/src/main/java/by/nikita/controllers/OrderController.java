package by.nikita.controllers;

import by.nikita.dto.OrderDto;
import by.nikita.services.api.IOrderService;
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
        orderService.addOrderByUser(orderDto);
        modelAndView.setViewName("redirect:/orders/username/?username={username}");
        return modelAndView;
    }

    @PostMapping(value = "/approved/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView approveOrderByAdmin(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        orderService.approveOrderByAdmin(id,id);
        modelAndView.setViewName("redirect:/orders/all");
        return modelAndView;
    }

    @GetMapping(value = "/username/")
    public ModelAndView getOrdersByUsername(@RequestParam String username) {
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
        OrderDto order = orderService.getOrderById(id);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("/views/orders/order");
        return modelAndView;
    }

    @GetMapping(value = "/number/{orderNumber}")
    public ModelAndView getOrderByNumber(@PathVariable Integer orderNumber) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDto order = orderService.getOrderByNumber(orderNumber);
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

    public ModelAndView getOrdersByUserFirstName(@RequestParam String firstName) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByUserFirstName(firstName);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/user_last_name/{lastName}")
    public ModelAndView getOrdersByUserLastName(@RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderDto> orders = orderService.getOrdersByUserLastName(lastName);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/views/orders/all_orders");
        return modelAndView;
    }

    @GetMapping(value = "/room_number/{roomNumber}")
    OrderDto getOrderByRoomNumber(@PathVariable Integer roomNumber) {
        return orderService.getOrderByRoomNumber(roomNumber);
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

    @GetMapping(value = "/edit_order/{id}")
    public ModelAndView showEditOrderForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/orders/edit_order");
        OrderDto orderDto = orderService.getOrderById(id);
        modelAndView.addObject("order", orderDto);
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editOrder(@PathVariable("id") long id,
                                  OrderDto orderDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/orders/get/" + id);
        orderService.updateOrder(id, orderDto);
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
