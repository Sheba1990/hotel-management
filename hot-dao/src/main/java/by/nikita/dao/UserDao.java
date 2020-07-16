package by.nikita.dao;

import by.nikita.dao.api.IUserDao;
import by.nikita.models.*;
import by.nikita.models.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao extends AbstractGenericDao<User> implements IUserDao {

    public UserDao() {
        super(User.class);
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), firstName));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.LAST_NAME), lastName));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByFullName(String firstName, String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            Predicate predicateForFirstName = criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), firstName);
            Predicate predicateForLastName = criteriaBuilder.equal(userDetails.get(UserDetails_.LAST_NAME), lastName);
            Predicate predicateForFullName = criteriaBuilder.and(predicateForFirstName, predicateForLastName);
            query.select(root).where(predicateForFullName);
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByPassportIssueCountry(String passportCountry) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            Join<UserDetails, Passport> passport = userDetails.join(UserDetails_.PASSPORT);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(passport.get(Passport_.COUNTRY_OF_ISSUE), passportCountry));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByResidenceCountry(String residenceCountry) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            Join<UserDetails, ContactData> contactData = userDetails.join(UserDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(address.get(Address_.COUNTRY), residenceCountry));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByResidenceCity(String residenceCity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, UserDetails> userDetails = root.join(User_.USER_DETAILS);
            Join<UserDetails, ContactData> contactData = userDetails.join(UserDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(address.get(Address_.CITY), residenceCity));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByOccupiedRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Join<User, Order> order = root.join(User_.ORDERS);
            Join<Order, Room> room = order.join(Order_.ROOM);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
