package by.nikita.dto;

import by.nikita.models.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends AbstractIdAwareDto {

    private Integer orderNumber;

    private boolean approved;

    private String userName;

    private String userEmail;

    private String userFullName;

    private Integer amountOfGuests;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCheckIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCheckOut;

    private Long stayingPeriod;

    private Integer roomNumber;

    private String roomCategory;

    public static List<OrderDto> convertList(List<Order> orderList) {
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderNumber(order.getOrderNumber());
            orderDto.setUserName(order.getUser().getUsername());
            if (order.getRoom()!= null) {
                orderDto.setRoomNumber(order.getRoom().getRoomNumber());
            } else {
                orderDto.setRoomNumber(null);
            }
            orderDto.setAmountOfGuests(order.getAmountOfGuests());
            orderDto.setRoomCategory(order.getRoomCategory());
            orders.add(orderDto);
        }
        return orders;
    }

    public static OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setApproved(order.isApproved());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            orderDto.setUserName(((UserDetails) principal).getUsername());
        } else {
            orderDto.setUserName(principal.toString());
        }
        if (order.getUser().getUserInDetails().getMiddleName() != null && !StringUtils.isEmpty(order.getUser().getUserInDetails().getMiddleName())) {
            orderDto.setUserFullName(order.getUser().getUserInDetails().getFirstName().concat(" " + order.getUser().getUserInDetails().getMiddleName() + " " + order.getUser().getUserInDetails().getLastName()));
        } else {
            orderDto.setUserFullName(order.getUser().getUserInDetails().getFirstName().concat(" " + order.getUser().getUserInDetails().getLastName()));
        }
        orderDto.setUserEmail(order.getUser().getEmail());
        orderDto.setAmountOfGuests(order.getAmountOfGuests());
        orderDto.setDateOfCheckIn(order.getDateOfCheckIn());
        orderDto.setDateOfCheckOut(order.getDateOfCheckOut());
        if (orderDto.getDateOfCheckIn() != null && orderDto.getDateOfCheckOut() != null) {
            LocalDate date1 = orderDto.getDateOfCheckIn();
            LocalDate date2 = orderDto.getDateOfCheckOut();
            Long amountOfDays = ChronoUnit.DAYS.between(date1, date2);
            orderDto.setStayingPeriod(amountOfDays);
        }
        if (order.getRoom()!= null) {
            orderDto.setRoomNumber(order.getRoom().getRoomNumber());
        } else {
            orderDto.setRoomNumber(null);
        }
        orderDto.setRoomCategory(order.getRoomCategory());
        return orderDto;
    }

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.approved = order.isApproved();
        this.amountOfGuests = order.getAmountOfGuests();
        this.dateOfCheckIn = order.getDateOfCheckIn();
        this.dateOfCheckOut = order.getDateOfCheckOut();
        this.userName = order.getUser().getUsername();
        this.userEmail = order.getUser().getEmail();
        this.roomNumber = order.getRoom().getRoomNumber();
        this.roomCategory = order.getRoom().getRoomCategory().getCategoryName();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Integer getAmountOfGuests() {
        return amountOfGuests;
    }

    public void setAmountOfGuests(Integer amountOfGuests) {
        this.amountOfGuests = amountOfGuests;
    }

    public LocalDate getDateOfCheckIn() {
        return dateOfCheckIn;
    }

    public void setDateOfCheckIn(LocalDate dateOfCheckIn) {
        this.dateOfCheckIn = dateOfCheckIn;
    }

    public LocalDate getDateOfCheckOut() {
        return dateOfCheckOut;
    }

    public void setDateOfCheckOut(LocalDate dateOfCheckOut) {
        this.dateOfCheckOut = dateOfCheckOut;
    }

    public Long getStayingPeriod() {
        return stayingPeriod;
    }

    public void setStayingPeriod(Long stayingPeriod) {
        this.stayingPeriod = stayingPeriod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }
}
