package by.nikita.dao;

import by.nikita.dao.api.IUserDao;
import by.nikita.models.User;
import by.nikita.models.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
            Root<User> root = query.from(User.class);
            query.select(root).where(criteriaBuilder.equal(root.get(User_.USERNAME), username));
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
}
