package com.hotel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHolder {
    private String dbDriver;
    private String jdbcUrl;
    private String dbUserName;
    private String dbUserPass;
    private Integer minPoolSize;
    private Integer maxPoolSize;
    private Integer maxIdleTime;
    private static final String DB_PROPERTY_FILE_NAME = "db.properties";
    private static final Logger logger = LogManager.getLogger(PropertyHolder.class);

    private static PropertyHolder instance;

    private PropertyHolder() {
        loadDBProperties();
    }

    public static synchronized PropertyHolder getInstance() {
        if (instance == null) {
            instance = new PropertyHolder();
        }
        return instance;
    }

    private void loadDBProperties() {
        Properties prop = new Properties();
        try (InputStream input = PropertyHolder.class.getClassLoader().getResourceAsStream(DB_PROPERTY_FILE_NAME)){
            if (input == null) {
                throw new IllegalArgumentException("Cannot find file db.properties");
            }
            prop.load(input);
            dbDriver = prop.getProperty("dbDriver");
            jdbcUrl = prop.getProperty("jdbcUrl");
            dbUserName = prop.getProperty("dbUserName");
            dbUserPass = prop.getProperty("dbUserPassword");
            minPoolSize = Integer.valueOf(prop.getProperty("minPoolSize"));
            maxPoolSize = Integer.valueOf(prop.getProperty("maxPoolSize"));
            maxIdleTime = Integer.valueOf(prop.getProperty("maxIdleTime"));
        } catch (IllegalArgumentException | IOException e) {
            logger.log(Level.ERROR, "Cannot load database properties", e);
            throw new IllegalStateException("Cannot load database properties", e);
        }
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbUserPass() {
        return dbUserPass;
    }

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public Integer getMaxIdleTime() {
        return maxIdleTime;
    }
}
