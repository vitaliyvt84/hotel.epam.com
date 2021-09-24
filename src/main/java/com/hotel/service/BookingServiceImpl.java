package com.hotel.service;

import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.BookingDTO;
import com.hotel.entity.Booking;
import com.hotel.exceptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements Service<Long, BookingDTO> {
    private Dao<Long, Booking> bookingDao;
    private static BookingServiceImpl instance;

    private BookingServiceImpl() {
        bookingDao = DaoFactory.getInstance().getBookingDao();
    }

    public static synchronized BookingServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookingServiceImpl();
        }
        return instance;
    }

    private BookingDTO mapBookingToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDateIn(booking.getDateIn());
        bookingDTO.setDateOut(booking.getDateOut());
        bookingDTO.setCreateTime(booking.getCreateTime());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setPrice(booking.getPrice());
        bookingDTO.setNumberOfAdult(booking.getNumberOfAdult());
        bookingDTO.setNumberOfChild(booking.getNumberOfChild());
        bookingDTO.setNumberOfRooms(booking.getNumberOfRooms());
        bookingDTO.setUserId(booking.getUserId());
        bookingDTO.setApartmentId(booking.getApartmentId());
        return bookingDTO;
    }

    private Booking mapBookingDTOToBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setDateIn(bookingDTO.getDateIn());
        booking.setDateOut(bookingDTO.getDateOut());
        booking.setStatus(bookingDTO.getStatus());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfAdult(bookingDTO.getNumberOfAdult());
        booking.setNumberOfChild(bookingDTO.getNumberOfChild());
        booking.setNumberOfRooms(bookingDTO.getNumberOfRooms());
        booking.setUserId(bookingDTO.getUserId());
        booking.setApartmentId(bookingDTO.getApartmentId());
        return booking;
    }

    @Override
    public List<BookingDTO> getAll() throws DBException {
        List<Booking> bookings = bookingDao.getAll();
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(mapBookingToBookingDTO(booking));
        }
        return bookingDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return bookingDao.countOfRows();
    }

    @Override
    public List<BookingDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<Booking> bookings = bookingDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(mapBookingToBookingDTO(booking));
        }
        return bookingDTOs;
    }

    @Override
    public BookingDTO getById(Long id) throws DBException {
        Booking booking = bookingDao.getById(id);
        BookingDTO bookingDTO = mapBookingToBookingDTO(booking);
        return bookingDTO;
    }

    public List<BookingDTO> getByUserId(Long id) throws DBException {
        List<Booking> bookings = bookingDao.getAllByValueFromColumn("account_id", String.valueOf(id));
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(mapBookingToBookingDTO(booking));
        }
        return bookingDTOs;
    }

    @Override
    public long save(BookingDTO bookingDTO) throws DBException {
        Booking booking = mapBookingDTOToBooking(bookingDTO);
        return bookingDao.save(booking);
    }

    @Override
    public int delete(Long id) throws DBException {
        return bookingDao.delete(id);
    }

    @Override
    public int update(BookingDTO bookingDTO) throws DBException {
        Booking booking = mapBookingDTOToBooking(bookingDTO);
        return bookingDao.update(booking);
    }
}
