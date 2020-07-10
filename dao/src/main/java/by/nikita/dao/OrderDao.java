package by.nikita.dao;

import by.nikita.dao.api.IOrderDao;
import by.nikita.models.Order;
import by.nikita.models.*;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AGenericDao<Order> implements IOrderDao {

    public OrderDao() {
        super(Order.class);
    }

    List<Order> getOrdersByUserFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Join<Order, User> user = root.join(Order_.USER);
            Join<User, UserDetails> userDetails = user.join(User_.USER_DETAILS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), firstName));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<Order> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
