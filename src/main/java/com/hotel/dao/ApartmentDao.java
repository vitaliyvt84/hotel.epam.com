package com.hotel.dao;

import com.hotel.entity.Entity;
import com.hotel.exceptions.DBException;

import java.util.List;

public interface ApartmentDao<K, T extends Entity<K>> extends Dao<K, T> {
    List<T> getAllWhereOrderByColumnOffsetNumOfRec(String orderByColumnName, Integer offset,
                                                   Integer numberOfRecords, Integer adultNum,
                                                   Integer childNum, Integer roomNum) throws DBException;
    long countOfRowsWhere(Integer adultNum, Integer childNum, Integer roomNum) throws DBException;
    List<T> getAllWhereThreeCondition(Integer adultNum, Integer childNum, Integer roomNum) throws DBException;
}
