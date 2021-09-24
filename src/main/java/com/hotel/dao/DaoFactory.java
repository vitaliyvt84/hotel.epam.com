package com.hotel.dao;

import com.hotel.dao.impl.*;
import com.hotel.entity.*;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {
        loadDaos();
    }

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    private ApartmentDao<Long, Apartment> apartmentDao;
    private Dao<Long, ApartmentClass> apartmentClassDao;
    private Dao<Long, ApartmentImage> apartmentImageDao;
    private Dao<Long, Booking> bookingDao;
    private Dao<Long, PreOrder> preOrderDao;
    private Dao<Long, Role> roleDao;
    private Dao<Long, User> userDao;

    private void loadDaos() {
        apartmentDao = new ApartmentDaoImpl();
        apartmentClassDao = new ApartmentClassDaoImpl();
        apartmentImageDao = new ApartmentImageDaoImpl();
        bookingDao = new BookingDaoImpl();
        preOrderDao = new PreOrderDaoImpl();
        roleDao = new RoleDaoImpl();
        userDao = new AccountDaoImpl();
    }

    public ApartmentDao<Long, Apartment> getApartmentDao() {
        return apartmentDao;
    }

    public Dao<Long, ApartmentClass> getApartmentClassDao() {
        return apartmentClassDao;
    }

    public Dao<Long, ApartmentImage> getApartmentImageDao() {
        return apartmentImageDao;
    }

    public Dao<Long, Booking> getBookingDao() {
        return bookingDao;
    }

    public Dao<Long, PreOrder> getPreOrderDao() {
        return preOrderDao;
    }

    public Dao<Long, Role> getRoleDao() {
        return roleDao;
    }

    public Dao<Long, User> getUserDao() {
        return userDao;
    }
}
