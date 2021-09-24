package com.hotel.dao;

import com.hotel.exceptions.DBException;
import com.hotel.entity.Entity;

import java.util.List;

/**
 * All classes that implement this interface uses to work with DB
 * @param <K> the type of id of specific class
 * @param <T> the type of specific class
 */
public interface Dao<K, T extends Entity<K>> {
    /**
     * Method have to use select query to return List of objects of class T form DB
     * @return List of specific objects
     * @throws DBException
     */
    List<T> getAll() throws DBException;

    /**
     * Method return object of class T from DB by id
     * @param key id value
     * @return object of class T
     * @throws DBException
     */
    T getById(K key) throws DBException;

    /**
     * Method return List of objects of class T from DB which are taken by specific value of specific column
     * @param columnName name of specific column
     * @param value by which we want to find some objects
     * @return List of specific objects
     * @throws DBException
     */
    List<T> getAllByValueFromColumn(String columnName, String value) throws DBException;

    /**
     * Method return List of objects of class T from DB which are Sorted by 'columnName', which are offset from
     * the first element by 'offset' and number of elements in the list is 'numberOfRecords'
     * @param columnName name of specific column
     * @param offset relative to the first element
     * @param numberOfRecords which will be taken from the DB
     * @return List of specific objects
     * @throws DBException
     */
    List<T> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException;

    /**
     * Method count the number of rows at specific table
     * @return number of rows
     * @throws DBException
     */
    long countOfRows() throws DBException;

    /**
     * Method save entity to DB
     *
     * @param entity that have to be saved
     * @return id of saved entity
     * @throws DBException
     */
    long save(T entity) throws DBException;

    /**
     * Method delete entry from DB by id
     * @param key id of entry
     * @return the number of rows that was deleted
     * @throws DBException
     */
    int delete(K key) throws DBException;

    /**
     * Method update entity in specific table at DB
     * @param entity that have to be updated
     * @return the number of rows that was updated
     * @throws DBException
     */
    int update(T entity) throws DBException;
}
