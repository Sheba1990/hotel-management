package by.nikita.dao.api;

import by.nikita.models.AEntity;

import java.util.List;

public interface IAGenericDao<T extends AEntity> {

    Class<T> getGenericClass();

    T create(T entity);

    T get(Long id);

    void update(T entity);

    List<T> getAll();

    void delete(T entity);
}
