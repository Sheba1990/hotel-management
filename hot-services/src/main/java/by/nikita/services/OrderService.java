package by.nikita.services;

import by.nikita.dao.api.IOrderDao;
import by.nikita.dao.api.IRoomDao;
import by.nikita.dao.api.IUserDao;
import by.nikita.dto.OrderDto;
import by.nikita.models.Order;
import by.nikita.models.Room;
import by.nikita.models.User;
import by.nikita.models.enums.RoomStatus;
import by.nikita.services.api.IOrderService;
import by.nikita.services.config.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

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
        room.setRoomStatus(RoomStatus.OCCUPIED);
        roomDao.update(room);
        orderDao.update(order);
        sendEmailFromAdmin(order);
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
    public List<OrderDto> getOrdersByRoomNumber(Integer roomNumber) {
        return OrderDto.convertList(orderDao.getOrdersByRoomNumber(roomNumber));
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
        order.setRoom(null);
        orderDao.update(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderDao.delete(orderDao.get(id));
    }

    void sendEmailFromAdmin(Order order) {
        String to = order.getUser().getEmail();
        String from = "norply@gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", emailProperties.getHost());
        properties.put("mail.smtp.port", emailProperties.getPort());
        properties.put("mail.smtp.ssl.enable", emailProperties.getSsl());
        properties.put("mail.smtp.auth", emailProperties.getAuth());
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProperties.getUsername(),
                        emailProperties.getPassword());
            }
        });

        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Hotel Horizon. Booking confirmation. Email notification for user: '" + order.getUser().getUsername() + "'.");

            LocalDate start = order.getDateOfCheckIn();
            LocalDate end = order.getDateOfCheckOut();
            long stayingPeriod = ChronoUnit.DAYS.between(start, end);
            double totalSumForStayingPeriod = stayingPeriod * order.getRoom().getRoomDetails().getPricePerNight();
            // Now set the actual message
            if (order.getUser().getUserInDetails().getFirstName() != null ||
                    order.getUser().getUserInDetails().getLastName() != null ||
                    order.getUser().getUserInDetails().getMiddleName() != null) {
                message.setContent("Hello Dear, " + order.getUser().getUserInDetails().getFirstName().concat(" " + order.getUser().getUserInDetails().getLastName()) + "!\n" +
                        "Your Booking, number " + order.getId() + ", has been approved by Administrator.\n" +
                        "Staying period " + stayingPeriod + " days.\n" +
                        "Room Number " + order.getRoom().getRoomNumber() + ".\n" +
                        "Total sum for staying period is " + totalSumForStayingPeriod + "0 $.", "text/html");
            } else {
                message.setContent("Hello Dear, " + order.getUser().getUsername() + "!\n" +
                        "Your Booking № " + order.getId() + " has been approved by Administrator.\n" +
                        "Staying period " + stayingPeriod + " day(s).\n" +
                        "Total sum for staying period is " + totalSumForStayingPeriod + "0 $.", "text/html");
            }
            // Send message
            Transport.send(message);
            System.out.println("Message has been sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
