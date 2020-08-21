package by.nikita.services.api;

import by.nikita.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto addOrderByUser(OrderDto orderDto);

    OrderDto acceptOrderByAdmin(long orderId, OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(long id);

    OrderDto getOrderByNumber(Integer orderNumber);

    List<OrderDto> getOrdersByRoomCategory(String roomCategory);

    List<OrderDto> getOrdersByUserFirstName(String firstName);

    List<OrderDto> getOrdersByUserLastName(String lastName);

    List<OrderDto> getOrdersByRoomNumber(Integer roomNumber);

    void updateOrder(long id, OrderDto orderDto);

    void deleteOrder(long id);

}
