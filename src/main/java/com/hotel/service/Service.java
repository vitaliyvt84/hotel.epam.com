package com.hotel.service;

import com.hotel.exceptions.DBException;

import java.util.List;

public interface Service<K,T> {
    List<T> getAll() throws DBException;
    T getById(K id) throws DBException;
    List<T> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException;
    long countOfRows() throws DBException;
    long save(T entity) throws DBException;
    int delete(K id) throws DBException;
    int update(T entity) throws DBException;
}
