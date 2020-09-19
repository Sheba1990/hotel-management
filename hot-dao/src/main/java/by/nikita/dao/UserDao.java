package by.nikita.dao;

import by.nikita.dao.api.IUserDao;
import by.nikita.models.Order;
import by.nikita.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDao extends AbstractGenericDao<User> implements IUserDao {

    public UserDao() {
        super(User.class);
    }

    @Override
    public User getByUsername(String username) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            query.select(user).where(criteriaBuilder.like(user.get(User_.USERNAME), "%" + username + "%"));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean checkUsernamePresence(String username) {
        return getByUsername(username) != null;
    }

    @Override
    public List<User> getUsersByUserFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            query.select(user).where(criteriaBuilder.like(userInDetails.get(UserInDetails_.FIRST_NAME), "%" + firstName + "%"));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByUserLastName(String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            query.select(user).where(criteriaBuilder.equal(userInDetails.get(UserInDetails_.LAST_NAME), lastName));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByFullName(String firstName, String lastName, String middleName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            Predicate predicateForFirstName = criteriaBuilder.equal(userInDetails.get(UserInDetails_.FIRST_NAME), firstName);
            Predicate predicateForLastName = criteriaBuilder.equal(userInDetails.get(UserInDetails_.LAST_NAME), lastName);
            Predicate predicateForMiddleName = criteriaBuilder.equal(userInDetails.get(UserInDetails_.MIDDLE_NAME), middleName);
            Predicate predicateForFullName = criteriaBuilder.or(
                    predicateForFirstName,
                    predicateForLastName,
                    predicateForMiddleName);
            query.select(user).where(predicateForFullName);
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
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            Join<UserInDetails, PassportData> passportData = userInDetails.join(UserInDetails_.PASSPORT_DATA);
            query.select(user).where(criteriaBuilder.equal(passportData.get(PassportData_.COUNTRY_OF_ISSUE), passportCountry));
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
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            Join<UserInDetails, ContactData> contactData = userInDetails.join(UserInDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            query.select(user).where(criteriaBuilder.equal(address.get(Address_.COUNTRY), residenceCountry));
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
            Root<User> user = query.from(User.class);
            Join<User, UserInDetails> userInDetails = user.join(User_.USER_IN_DETAILS);
            Join<UserInDetails, ContactData> contactData = userInDetails.join(UserInDetails_.CONTACT_DATA);
            Join<ContactData, Address> address = contactData.join(ContactData_.ADDRESS);
            query.select(user).where(criteriaBuilder.equal(address.get(Address_.CITY), residenceCity));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserByOccupiedRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Join<User, Order> order = user.join(User_.ORDER);
            Join<Order, Room> room = order.join(Order_.ROOM);
            query.select(user).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
