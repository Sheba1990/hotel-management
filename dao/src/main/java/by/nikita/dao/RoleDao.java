package by.nikita.dao;

import by.nikita.dao.api.IRoleDao;
import by.nikita.models.Role;

public class RoleDao extends AGenericDao<Role> implements IRoleDao {
    public RoleDao() {
        super(Role.class);
    }
}
