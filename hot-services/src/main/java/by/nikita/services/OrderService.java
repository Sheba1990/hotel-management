package by.nikita.services;

import by.nikita.dao.api.IOrderDao;
import by.nikita.dao.api.IRoomDao;
import by.nikita.dto.OrderDto;
import by.nikita.models.Order;
import by.nikita.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    IOrderDao orderDao;

    @Autowired
    IRoomDao roomDao;

    @Override
    public OrderDto addOrderByUser(OrderDto orderDto) {
        Order order = new Order();
        order.setRoomCategory(orderDto.getRoomCategory());
        order.setAmountOfGuests(orderDto.getAmountOfGuests());
        order.setDateOfCheckIn(orderDto.getDateOfCheckIn());
        order.setDateOfCheckOut(orderDto.getDateOfCheckOut());
        order.setApproved(false);
        return OrderDto.entityToDto(orderDao.create(order));
    }

    public OrderDto acceptOrderByAdmin(long orderId, OrderDto orderDto) {
        Order order = orderDao.get(orderId);
        order.setApproved(true);
        orderDao.update(order);
        return OrderDto.entityToDto(order);
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
        Order order = orderDao.get(id);
        if (orderDto.getAmountOfGuests() != null && !StringUtils.isEmpty(orderDto.getAmountOfGuests())) {
            order.setAmountOfGuests(orderDto.getAmountOfGuests());
        }
        if (orderDto.getDateOfCheckIn() != null && !StringUtils.isEmpty(orderDto.getDateOfCheckIn())) {
            order.setDateOfCheckIn(orderDto.getDateOfCheckIn());
        }
        if (orderDto.getDateOfCheckOut() != null && !StringUtils.isEmpty(orderDto.getDateOfCheckOut())) {
            order.setDateOfCheckOut(orderDto.getDateOfCheckOut());
        }
        orderDao.update(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderDao.delete(orderDao.get(id));
    }
}
