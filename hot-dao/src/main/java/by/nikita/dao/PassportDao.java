package by.nikita.dao;

import by.nikita.dao.api.IPassportDao;
import by.nikita.models.Passport;
import org.springframework.stereotype.Repository;

@Repository
public class PassportDao extends AbstractGenericDao<Passport> implements IPassportDao {
    public PassportDao() {
        super(Passport.class);
    }
}
