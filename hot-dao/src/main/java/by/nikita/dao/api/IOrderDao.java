package by.nikita.dao.api;

import by.nikita.models.Order;

import java.util.List;

public interface IOrderDao extends IAbstractGenericDao<Order> {

    List<Order> getOrdersByRoomCategory(String roomCategory);

    public List<Order> getOrdersByUsername(String username);

    List<Order> getOrdersByUserFirstName(String firstName);

    List<Order> getOrdersByUserLastName(String lastName);

    Order getOrderByRoomNumber(Integer roomNumber);

    List<Order> getAllNotApprovedOrders();

    List<Order> getAllApprovedOrders();

}
