package by.nikita.dao;

import by.nikita.dao.api.IUserDao;
import by.nikita.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDao extends AGenericDao<User> implements IUserDao {

    public UserDao() {
        super(User.class);
    }

    public List<User> getUsersByFirstName(String firstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery
        }
    }
}
