package by.nikita.dao;

import by.nikita.dao.api.IPassportDao;
import by.nikita.models.Passport;

public class PassportDao extends AGenericDao<Passport> implements IPassportDao {
    public PassportDao() {
        super(Passport.class);
    }
}
