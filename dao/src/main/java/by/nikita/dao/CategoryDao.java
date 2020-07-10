package by.nikita.dao;

import by.nikita.dao.api.ICategoryDao;
import by.nikita.models.Category;

public class CategoryDao extends AGenericDao<Category> implements ICategoryDao {

    public CategoryDao() {
        super(Category.class);
    }
}
