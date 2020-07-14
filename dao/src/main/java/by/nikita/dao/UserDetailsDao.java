package by.nikita.dao;

import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.models.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsDao extends AbstractGenericDao<UserDetails> implements IUserDetailsDao {

    public UserDetailsDao() {
        super(UserDetails.class);
    }

    public List<UserDetails> getDetailsByUserFirstName(String firstName) {
        return null;
    }
}
