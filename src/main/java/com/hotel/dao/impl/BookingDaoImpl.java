package com.hotel.dao.impl;

import com.hotel.entity.BookingStatus;
import com.hotel.entity.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.INSERT_BOOKING;
import static com.hotel.dao.SQLConstants.UPDATE_BOOKING;

public class BookingDaoImpl extends CrudDAO<Booking> {
    public BookingDaoImpl() {
        super("booking");
    }

    private void setStatement(Booking entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setDate(++k, new Date(entity.getDateIn().getTime()));
        pstmt.setDate(++k, new Date(entity.getDateOut().getTime()));
        pstmt.setInt(++k, entity.getStatus().getValue());
        pstmt.setDouble(++k, entity.getPrice());
        pstmt.setInt(++k, entity.getNumberOfAdult());
        pstmt.setInt(++k, entity.getNumberOfChild());
        pstmt.setInt(++k, entity.getNumberOfRooms());
        pstmt.setLong(++k, entity.getUserId());
        pstmt.setLong(++k, entity.getApartmentId());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, Booking entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_BOOKING);
        setStatement(entity, pstmt);
        pstmt.setLong(10, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, Booking entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<Booking> readAll(ResultSet rs) throws SQLException {
        List<Booking> result = new ArrayList<>();
        Booking booking = null;
        while (rs.next()) {
            booking = new Booking();
            booking.setId(rs.getLong("id"));
            booking.setDateIn(rs.getDate("date_in"));
            booking.setDateOut(rs.getDate("date_out"));
            booking.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            booking.setStatus(BookingStatus.getBookingStatusByValue(rs.getInt("status")));
            booking.setPrice(rs.getDouble("price"));
            booking.setNumberOfAdult(rs.getInt("number_of_adult"));
            booking.setNumberOfChild(rs.getInt("number_of_child"));
            booking.setNumberOfRooms(rs.getInt("number_of_rooms"));
            booking.setUserId(rs.getLong("account_id"));
            booking.setApartmentId(rs.getLong("apartment_id"));
            result.add(booking);
        }
        return result;
    }
}
