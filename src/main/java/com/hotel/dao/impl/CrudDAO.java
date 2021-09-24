package com.hotel.dao.impl;

import com.hotel.exceptions.DBException;
import com.hotel.DataSource;
import com.hotel.dao.Dao;
import com.hotel.entity.Entity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.*;

public abstract class CrudDAO<T extends Entity<Long>> implements Dao<Long, T> {
    private String tableName;
    private DataSource dataSource;
    private static Logger logger = LogManager.getLogger(CrudDAO.class);

    public CrudDAO(String tableName) {
        this.tableName = tableName;
        dataSource = DataSource.getInstance();
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<T> getAll() throws DBException {
        List<T> result = null;
        String sql = String.format(SELECT_ALL, tableName);

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            con.setAutoCommit(true);

            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get all elements from table " + tableName, e);
            throw new DBException("Cannot get all elements from table " + tableName, e);
        }
        return result;
    }

    @Override
    public long countOfRows() throws DBException {
        long resultCount = 0L;
        String sql = String.format(SELECT_COUNT, tableName);

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            con.setAutoCommit(true);

            if(rs.next()) {
                resultCount = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get count of rows from table " + tableName, e);
            throw new DBException("Cannot get count of rows from table " + tableName, e);
        }
        return resultCount;
    }

    @Override
    public T getById(Long id) throws DBException {
        List<T> result = null;
        String sql = String.format(FIND_BY_ID, tableName);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setLong(++k, id);
            rs = pstmt.executeQuery();
            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get element by id = "+ id +", from table " + tableName, e);
            throw new DBException("Cannot get element by id = "+ id +", from table " + tableName, e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public List<T> getAllByValueFromColumn(String columnName, String value) throws DBException {
        List<T> result = null;
        String sql = String.format(FIND_BY, tableName, columnName);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setString(++k, value);
            rs = pstmt.executeQuery();
            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get elements by column '"+ columnName + "' by value = "+ value +", from table " + tableName, e);
            throw new DBException("Cannot get elements by column '"+ columnName + "' by value = "+ value +", from table " + tableName, e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public List<T> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<T> result = null;
        String sql = String.format(SELECT_ALL_ORDER_BY_LIMIT, tableName, columnName);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            con.setAutoCommit(true);

            int k = 0;
            pstmt.setInt(++k, offset);
            pstmt.setInt(++k, numberOfRecords);
            rs = pstmt.executeQuery();
            result = readAll(rs);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot get all elements from table " + tableName, e);
            throw new DBException("Cannot get all elements from table " + tableName, e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public long save(T entity) throws DBException {
        long result = 0;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = createInsertStatement(con, entity);

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getLong(1);
                entity.setId(result);
            }
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot save element with id = "+ entity.getId() +", to table " + tableName, e);
            DataSource.rollBack(con);
            throw new DBException("Cannot save element with id = "+ entity.getId() +", to table " + tableName, e);
        } finally {
            DataSource.close(rs);
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public int delete(Long id) throws DBException {
        int result;
        String sql = String.format(DELETE_BY_ID, tableName);

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);

            int k = 0;
            pstmt.setLong(++k, id);
            result = pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot delete element by id = "+ id +", from table " + tableName, e);
            DataSource.rollBack(con);
            throw new DBException("Cannot delete element by id = "+ id +", from table " + tableName, e);
        } finally {
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    @Override
    public int update(T entity) throws DBException {
        int result;

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();
            pstmt = createUpdateStatement(con, entity);

            result = pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.WARN, "Cannot update element with id = "+ entity.getId() +", in table " + tableName, e);
            DataSource.rollBack(con);
            throw new DBException("Cannot update element with id = "+ entity.getId() +", in table " + tableName, e);
        } finally {
            DataSource.close(pstmt);
            //DataSource.close(con);
        }
        return result;
    }

    /**
     * This method must implement all subclasses. It create and prepare PreparedStatement on specific Connection
     * for update operation at DB
     * @param con specific connection
     * @param entity that should be updated
     * @return PreparedStatement that corresponds to entity and update operation
     * @throws SQLException
     */
    protected abstract PreparedStatement createUpdateStatement(Connection con, T entity) throws SQLException;

    /**
     * This method must implement all subclasses. It create and prepare PreparedStatement on specific Connection
     * for insert operation at DB
     * @param con specific connection
     * @param entity that should be inserted
     * @return PreparedStatement that corresponds to entity and insert operation
     * @throws SQLException
     */
    protected abstract PreparedStatement createInsertStatement(Connection con, T entity) throws SQLException;

    /**
     * This method must implement all subclasses. It uses in all methods of this class that select some values from DB.
     * It uses to get all values from result set and save them to specific objects.
     * It returns result set after operation selectIt create and prepare PreparedStatement on specific Connection
     * for insert operation at DB
     * @param rs received after a query to DB
     * @return List of specific objects
     * @throws SQLException
     */
    protected abstract List<T> readAll(ResultSet rs) throws SQLException;
}
