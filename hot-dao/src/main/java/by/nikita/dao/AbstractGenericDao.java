package by.nikita.dao;

import by.nikita.dao.api.IAbstractGenericDao;
import by.nikita.models.AbstractIdAwareEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGenericDao<T extends AbstractIdAwareEntity> implements IAbstractGenericDao<T> {

    protected Class<T> clazz;

    public AbstractGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getGenericClass() {
        return this.clazz;
    }

    @Autowired
    protected EntityManager entityManager;

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T get(Long id) {
        return entityManager.find(getGenericClass(), id);
    }

    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public List<T> getAll() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(getGenericClass());
            Root<T> root = query.from(getGenericClass());
            query.select(root);
            TypedQuery<T> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
