package by.nikita.dao.api;

import by.nikita.models.IdAwareEntity;

import java.util.List;

public interface IAbstractGenericDao<T extends IdAwareEntity> {

    Class<T> getGenericClass();

    T create(T entity);

    T get(Long id);

    void update(T entity);

    List<T> getAll();

    void delete(T entity);
}
