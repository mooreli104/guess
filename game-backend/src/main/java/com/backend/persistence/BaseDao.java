package com.backend.persistence;

public interface BaseDao<T> {
    void save(T object);

    void update();

    void merge(T object);

    void remove(T object);

    T findById(String id);

}
