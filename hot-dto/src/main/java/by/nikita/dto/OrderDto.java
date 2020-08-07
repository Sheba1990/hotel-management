package by.nikita.dto;

import by.nikita.models.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto extends AbstractIdAwareDto {

    private Integer orderNumber;

    private Integer amountOfGuests;

    private LocalDate dateOfCheckIn;

    private LocalDate dateOfCheckOut;

    private String userName;

    private String userEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userFirstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userLastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer roomNumber;

    private String roomCategory;

    public static List<OrderDto> convertList(List<Order> orderList) {
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderNumber(order.getOrderNumber());
            orderDto.setUserName(order.getUser().getUsername());
            orderDto.setRoomNumber(order.getRoom().getRoomNumber());
            orderDto.setAmountOfGuests(order.getAmountOfGuests());
            orderDto.setRoomCategory(order.getRoom().getRoomCategory().getCategoryName());
            orders.add(orderDto);
        }
        return orders;
    }

    public static OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setAmountOfGuests(order.getAmountOfGuests());
        orderDto.setDateOfCheckIn(order.getDateOfCheckIn());
        orderDto.setDateOfCheckOut(order.getDateOfCheckOut());
        orderDto.setUserName(order.getUser().getUsername());
        orderDto.setUserEmail(order.getUser().getEmail());
        orderDto.setUserFirstName(order.getUser().getUserDetails().getFirstName());
        orderDto.setUserLastName(order.getUser().getUserDetails().getLastName());
        orderDto.setRoomNumber(order.getRoom().getRoomNumber());
        orderDto.setRoomCategory(order.getRoom().getRoomCategory().getCategoryName());
        return orderDto;
    }

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.amountOfGuests = order.getAmountOfGuests();
        this.dateOfCheckIn = order.getDateOfCheckIn();
        this.dateOfCheckOut = order.getDateOfCheckOut();
        this.userName = order.getUser().getUsername();
        this.userEmail = order.getUser().getEmail();
        this.userFirstName = order.getUser().getUserDetails().getFirstName();
        this.userLastName = order.getUser().getUserDetails().getLastName();
        this.roomNumber = order.getRoom().getRoomNumber();
        this.roomCategory = order.getRoom().getRoomCategory().getCategoryName();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
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
