package com.hotel.dao.impl;

import com.hotel.DataSource;
import com.hotel.dao.ApartmentDao;
import com.hotel.entity.Apartment;
import com.hotel.entity.ApartmentStatus;
import com.hotel.entity.BookingStatus;
import com.hotel.exceptions.DBException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.*;

public class ApartmentDaoImpl extends CrudDAO<Apartment> implements ApartmentDao<Long, Apartment> {
    private static Logger logger = LogManager.getLogger(ApartmentDaoImpl.class);

    public ApartmentDaoImpl() {
        super("apartment");
    }

    @Override
    public List<Apartment> getAllWhereOrderByColumnOffsetNumOfRec(String orderByColumnName, Integer offset,
                                                                  Integer numberOfRecords, Integer adultNum,
                                                                  Integer childNum, Integer roomNum) throws DBException {
        List<Apartment> result = null;
        String sql = String.format(SELECT_ALL_FROM_APARTMENT_WHERE_ORDER_BY_LIMIT, orderByColumnName);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getDataSource().getConnection();
            pstmt = con.prepareStatement(sql);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setInt(++k, adultNum);
            pstmt.setInt(++k, childNum);
            pstmt.setInt(++k, roomNum);
            pstmt.setInt(++k, offset);
            pstmt.setInt(++k, numberOfRecords);
            rs = pstmt.executeQuery();
            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get all elements from table Apartment", e);
            throw new DBException("Cannot get all elements from table Apartment", e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public List<Apartment> getAllWhereThreeCondition(Integer adultNum, Integer childNum, Integer roomNum) throws DBException {
        List<Apartment> result = null;
        String sql = String.format(SELECT_ALL_FROM_APARTMENT_WHERE);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getDataSource().getConnection();
            pstmt = con.prepareStatement(sql);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setInt(++k, adultNum);
            pstmt.setInt(++k, childNum);
            pstmt.setInt(++k, roomNum);
            rs = pstmt.executeQuery();
            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get all elements from table Apartment", e);
            throw new DBException("Cannot get all elements from table Apartment", e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public long countOfRowsWhere(Integer adultNum, Integer childNum, Integer roomNum) throws DBException {
        long resultCount = 0L;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getDataSource().getConnection();
            pstmt = con.prepareStatement(SELECT_COUNT_FROM_APARTMENT_WHERE);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setInt(++k, adultNum);
            pstmt.setInt(++k, childNum);
            pstmt.setInt(++k, roomNum);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                resultCount = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get count of rows from Apartment", e);
            throw new DBException("Cannot get count of rows from Apartment", e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return resultCount;
    }

    private void setStatement(Apartment entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setInt(++k, entity.getNumber());
        pstmt.setString(++k, entity.getName());
        pstmt.setDouble(++k, entity.getPrice());
        pstmt.setInt(++k, entity.getMaxCountOfAdult());
        pstmt.setInt(++k, entity.getMaxCountOfChild());
        pstmt.setInt(++k, entity.getStatus().getValue());
        pstmt.setInt(++k, entity.getCountOfRoom());
        pstmt.setString(++k, entity.getDescription());
        pstmt.setInt(++k, entity.getNumberOfBed());
        pstmt.setLong(++k, entity.getApartmentClassId());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, Apartment entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_APARTMENT);
        setStatement(entity, pstmt);
        pstmt.setLong(11, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, Apartment entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_APARTMENT, Statement.RETURN_GENERATED_KEYS);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<Apartment> readAll(ResultSet rs) throws SQLException {
        List<Apartment> result = new ArrayList<>();
        Apartment apartment = null;
        while (rs.next()) {
            apartment = new Apartment();
            apartment.setId(rs.getLong("id"));
            apartment.setNumber(rs.getInt("number"));
            apartment.setName(rs.getString("name"));
            apartment.setPrice(rs.getDouble("price"));
            apartment.setMaxCountOfAdult(rs.getInt("max_count_adult"));
            apartment.setMaxCountOfChild(rs.getInt("max_count_child"));
            apartment.setStatus(ApartmentStatus.getApartmentStatusByValue(rs.getInt("status")));
            apartment.setCountOfRoom(rs.getInt("count_of_room"));
            apartment.setDescription(rs.getString("description"));
            apartment.setNumberOfBed(rs.getInt("number_of_bed"));
            apartment.setApartmentClassId(rs.getLong("apartment_class_id"));
            result.add(apartment);
        }
        return result;
    }
}