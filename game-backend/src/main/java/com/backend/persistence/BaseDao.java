package com.backend.persistence;

public interface BaseDao<T> {
    void save(T object);

    void update();

    T findById(String id);

}
