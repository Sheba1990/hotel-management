package by.nikita.services.api;

import by.nikita.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto addOrderByUser(String username, OrderDto orderDto);

    OrderDto acceptOrderByAdmin(long orderId, OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(long id);

    OrderDto getOrderByNumber(Integer orderNumber);

    List<OrderDto> getOrdersByRoomCategory(String roomCategory);

    List<OrderDto> getOrdersByUserFirstName(String firstName);

    List<OrderDto> getOrdersByUserLastName(String lastName);

    OrderDto getOrderByRoomNumber(Integer roomNumber);

    List<OrderDto> getAllNotApprovedOrders();

    List<OrderDto> getAllApprovedOrders();

    void updateOrder(long id, OrderDto orderDto);

    void deleteOrder(long id);

}
