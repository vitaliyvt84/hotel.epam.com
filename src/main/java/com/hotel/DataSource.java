package com.hotel;

import com.hotel.exceptions.DBException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.*;

public class DataSource {

    private static ComboPooledDataSource poolConnections;
    private static final Logger logger = LogManager.getLogger(DataSource.class);

    private static DataSource instance;

    private DataSource() {
        initPoolConnections();
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection con = poolConnections.getConnection();
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return con;
    }

    private void initPoolConnections() {
        PropertyHolder pHolder = PropertyHolder.getInstance();
        poolConnections = new ComboPooledDataSource();
        try {
            poolConnections.setDriverClass(pHolder.getDbDriver());
            poolConnections.setJdbcUrl(pHolder.getJdbcUrl());
            poolConnections.setUser(pHolder.getDbUserName());
            poolConnections.setPassword(pHolder.getDbUserPass());
            poolConnections.setMinPoolSize(pHolder.getMinPoolSize());
            poolConnections.setMaxPoolSize(pHolder.getMaxPoolSize());
            poolConnections.setMaxIdleTime(pHolder.getMaxIdleTime());
        } catch (PropertyVetoException e) {
            logger.log(Level.ERROR, "Cannot init pool connections", e);
            throw new IllegalStateException("Cannot init pool connections", e);
        }
    }

    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                logger.warn("Cannot close: ", e);
            }
        }
    }
    public static void rollBack(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                logger.warn("Cannot rollback transaction", e);
            }
        }
    }
    public static void main(String[] args) throws SQLException, DBException {
        Connection connection = DataSource.getInstance().getConnection();
        //SelectCreate.getInstance().insertUser(new User("Igor", "igor@g.com", "567"));
        System.out.println(connection);
    }
}
