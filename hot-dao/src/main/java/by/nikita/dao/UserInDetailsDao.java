package by.nikita.dao;

import by.nikita.dao.api.IUserInDetailsDao;
import by.nikita.models.Order;
import by.nikita.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserInDetailsDao extends AbstractGenericDao<UserInDetails> implements IUserInDetailsDao {

    public UserInDetailsDao() {
        super(UserInDetails.class);
    }

    @Override
    public UserInDetails getUserDetailsByUserId(long userId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, User> user = root.join(UserInDetails_.USER);
            query.select(root).where(criteriaBuilder.equal(user.get(User_.ID), userId));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserInDetails getUserDetailsByUsername(String username) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, User> user = root.join(UserInDetails_.USER);
            query.select(root).where(criteriaBuilder.equal(user.get(User_.USERNAME), username));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UserInDetails> getUserDetailsByUserFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            query.select(root).where(criteriaBuilder.equal(root.get(UserInDetails_.FIRST_NAME), firstName));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UserInDetails> getUserDetailsByUserLastName(String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            query.select(root).where(criteriaBuilder.equal(root.get(UserInDetails_.LAST_NAME), lastName));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserInDetails> getUserDetailsByFullName(String firstName, String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Predicate predicateForFirstName = criteriaBuilder.equal(root.get(UserInDetails_.FIRST_NAME), firstName);
            Predicate predicateForLastName = criteriaBuilder.equal(root.get(UserInDetails_.LAST_NAME), lastName);
            Predicate predicateForFullName = criteriaBuilder.and(predicateForFirstName, predicateForLastName);
            query.select(root).where(predicateForFullName);
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserInDetails> getUsersByPassportIssueCountry(String passportCountry) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, PassportData> passport = root.join(UserInDetails_.PASSPORT_DATA);
            query.select(root).where(criteriaBuilder.equal(passport.get(PassportData_.COUNTRY_OF_ISSUE), passportCountry));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserInDetails> getUsersByResidenceCountry(String residenceCountry) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, ContactData> contactData = root.join(UserInDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            query.select(root).where(criteriaBuilder.equal(address.get(Address_.COUNTRY), residenceCountry));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserInDetails> getUsersByResidenceCity(String residenceCity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, ContactData> contactData = root.join(UserInDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            query.select(root).where(criteriaBuilder.equal(address.get(Address_.CITY), residenceCity));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserInDetails getUserByOccupiedRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserInDetails> query = criteriaBuilder.createQuery(UserInDetails.class);
            Root<UserInDetails> root = query.from(UserInDetails.class);
            Join<UserInDetails, User> user = root.join(UserInDetails_.USER);
            Join<User, Order> order = user.join(User_.ORDER);
            Join<Order, Room> room = order.join(Order_.ROOM);
            query.select(root).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<UserInDetails> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
