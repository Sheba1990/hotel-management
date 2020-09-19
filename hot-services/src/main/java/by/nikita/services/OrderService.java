package by.nikita.services;

import by.nikita.dao.api.IOrderDao;
import by.nikita.dao.api.IRoomDao;
import by.nikita.dao.api.IUserDao;
import by.nikita.dto.OrderDto;
import by.nikita.dto.RoomDto;
import by.nikita.models.Order;
import by.nikita.models.Room;
import by.nikita.models.User;
import by.nikita.services.api.IOrderService;
import by.nikita.services.config.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public OrderDto addOrderByUser(OrderDto orderDto) {
        Order order = new Order();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            User user = userDao.getByUsername(username);
            order.setUser(user);
        }
        order.setRoomCategory(orderDto.getRoomCategory());
        order.setAmountOfGuests(orderDto.getAmountOfGuests());
        order.setDateOfCheckIn(orderDto.getDateOfCheckIn());
        order.setDateOfCheckOut(orderDto.getDateOfCheckOut());
        order.setApproved(false);
        return OrderDto.entityToDto(orderDao.create(order));
    }

    public void approveOrderByAdmin(long orderId, long roomId) {
        Order order = orderDao.get(orderId);
        Room room = roomDao.get(roomId);
        order.setRoom(room);
        order.setApproved(true);
        orderDao.update(order);
        OrderDto.entityToDto(order);
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
    public List<OrderDto> getOrdersByRoomCategory(String roomCategory) {
        return OrderDto.convertList(orderDao.getOrdersByRoomCategory(roomCategory));
    }

    @Override
    public List<OrderDto> getOrdersByUsername(String username) {
        return OrderDto.convertList(orderDao.getOrdersByUsername(username));
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
    public OrderDto getOrderByRoomNumber(Integer roomNumber) {
        return OrderDto.entityToDto(orderDao.getOrderByRoomNumber(roomNumber));
    }

    @Override
    public List<OrderDto> getAllNotApprovedOrders() {
        return OrderDto.convertList(orderDao.getAllNotApprovedOrders());
    }

    @Override
    public List<OrderDto> getAllApprovedOrders() {
        return OrderDto.convertList(orderDao.getAllApprovedOrders());
    }

    @Override
    public void editOrder(long id, OrderDto orderDto) {
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
        order.setRoomCategory(orderDto.getRoomCategory());
        order.setApproved(false);
        orderDao.update(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderDao.delete(orderDao.get(id));
    }
}
