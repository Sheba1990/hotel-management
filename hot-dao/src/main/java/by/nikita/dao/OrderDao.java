package by.nikita.dao;

import by.nikita.dao.api.IOrderDao;
import by.nikita.models.Order;
import by.nikita.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao extends AbstractGenericDao<Order> implements IOrderDao {

    public OrderDao() {
        super(Order.class);
    }


    //Get mapping methods

    public List<Order> getOrdersByRoomCategory(String roomCategory) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, Room> room = root.join(Order_.ROOM);
            Join<Room, RoomCategory> category = room.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.like(criteriaBuilder.upper(category.get(RoomCategory_.CATEGORY_NAME)), roomCategory.toUpperCase()));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, Room> room = root.join(Order_.ROOM);
            query.select(root).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> getOrdersByUsername(String username) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, User> user = root.join(Order_.USER);
            query.select(root).where(criteriaBuilder.like(criteriaBuilder.upper(user.get(User_.USERNAME)), "%" + username.toUpperCase() + "%"));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> getOrdersByUserFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, User> user = root.join(Order_.USER);
            Join<User, UserInDetails> userDetails = user.join(User_.USER_IN_DETAILS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(userDetails.get(UserInDetails_.FIRST_NAME), firstName));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> getOrdersByUserLastName(String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, User> user = root.join(Order_.USER);
            Join<User, UserInDetails> userDetails = user.join(User_.USER_IN_DETAILS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(userDetails.get(UserInDetails_.LAST_NAME), lastName));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> getAllNotApprovedOrders() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Order_.APPROVED), false));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> getAllApprovedOrders() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Order_.APPROVED), true));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
