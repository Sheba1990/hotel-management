package by.nikita.services;

import by.nikita.dao.api.IOrderDao;
import by.nikita.dto.OrderDto;
import by.nikita.models.Order;
import by.nikita.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    IOrderDao orderDao;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setNumber(orderDto.getOrderNumber());
        order.setAmountOfGuests(orderDto.getAmountOfGuests());
        order.setDateOfCheckIn(orderDto.getDateOfCheckIn());
        order.setDateOfCheckOut(orderDto.getDateOfCheckOut());
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return OrderDto.convertList(orderDao.getAll());
    }

    @Override
    public OrderDto getOrderById(long id) {
        return OrderDto.entityToDto(orderDao.get(id));
    }

    @Override
    public OrderDto getOrderByNumber(Integer orderNumber) {
        return OrderDto.entityToDto(orderDao.getOrderByNumber(orderNumber));
    }

    @Override
    public List<OrderDto> getOrdersByRoomCategory(String roomCategory) {
        return OrderDto.convertList(orderDao.getOrdersByRoomCategory(roomCategory));
    }

    @Override
    public List<OrderDto> getOrdersByUserFirstName(String firstName) {
        return OrderDto.convertList(orderDao.getOrdersByUserFirstName(firstName));
    }

    @Override
    public List<OrderDto> getOrdersByUserLastName(String lastName) {
        return OrderDto.convertList(orderDao.getOrdersByUserLastName(lastName));
    }

    @Override
    public List<OrderDto> getOrdersByRoomNumber(Integer roomNumber) {
        return OrderDto.convertList(orderDao.getOrdersByRoomNumber(roomNumber));
    }

    @Override
    public void updateOrder(long id, OrderDto orderDto) {

    }

    @Override
    public void deleteOrder(long id) {
        orderDao.delete(orderDao.get(id));
    }
}
