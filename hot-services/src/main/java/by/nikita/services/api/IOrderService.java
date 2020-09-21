package by.nikita.services.api;

import by.nikita.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto addOrderByUser(OrderDto orderDto);

    void approveOrderByAdmin(long orderId, long roomId);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(long id);

    List<OrderDto> getOrdersByRoomCategory(String roomCategory);

    List<OrderDto> getOrdersByUsername(String username);

    List<OrderDto> getOrdersByUserFirstName(String firstName);

    List<OrderDto> getOrdersByUserLastName(String lastName);

    List<OrderDto> getOrdersByRoomNumber(Integer roomNumber);

    List<OrderDto> getAllNotApprovedOrders();

    List<OrderDto> getAllApprovedOrders();

    void editOrder(long id, OrderDto orderDto);

    void deleteOrder(long id);

}
