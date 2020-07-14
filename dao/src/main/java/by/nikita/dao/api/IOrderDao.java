package by.nikita.dao.api;

import by.nikita.models.Order;

import java.util.List;

public interface IOrderDao extends IAGenericDao<Order> {

    Order getOrderByNumber(Integer orderNumber);

    List<Order> getOrdersByRoomCategory(String roomCategory);

    List<Order> getOrdersByUserFirstName(String firstName);

    List<Order> getOrdersByUserLastName(String lastName);

}
