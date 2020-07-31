package by.nikita.dao;

import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.models.User;
import by.nikita.models.UserDetails;
import by.nikita.models.UserDetails_;
import by.nikita.models.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDetailsDao extends AbstractGenericDao<UserDetails> implements IUserDetailsDao {

    public UserDetailsDao() {
        super(UserDetails.class);
    }

    @Override
    public UserDetails getUserDetailsByUserId(long userId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserDetails> query = criteriaBuilder.createQuery(UserDetails.class);
            Root<UserDetails> root = query.from(UserDetails.class);
            Join<UserDetails, User> user = root.join(UserDetails_.USER);
            query.select(root).where(criteriaBuilder.equal(user.get(User_.ID), userId));
            TypedQuery<UserDetails> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UserDetails> getUserDetailsByUserFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserDetails> query = criteriaBuilder.createQuery(UserDetails.class);
            Root<UserDetails> root = query.from(UserDetails.class);
            query.select(root).where(criteriaBuilder.equal(root.get(UserDetails_.FIRST_NAME), firstName));
            TypedQuery<UserDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UserDetails> getUserDetailsByUserLastName(String lastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserDetails> query = criteriaBuilder.createQuery(UserDetails.class);
            Root<UserDetails> root = query.from(UserDetails.class);
            query.select(root).where(criteriaBuilder.equal(root.get(UserDetails_.LAST_NAME), lastName));
            TypedQuery<UserDetails> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
