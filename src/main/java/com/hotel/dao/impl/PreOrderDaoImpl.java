package com.hotel.dao.impl;

import com.hotel.entity.PreOrder;
import com.hotel.entity.PreOrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.INSERT_PRE_ORDER;
import static com.hotel.dao.SQLConstants.UPDATE_PRE_ORDER;

public class PreOrderDaoImpl extends CrudDAO<PreOrder> {
    public PreOrderDaoImpl() {
        super("pre_order");
    }

    private void setStatement(PreOrder entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setInt(++k, entity.getNumberOfAdult());
        pstmt.setInt(++k, entity.getNumberOfChild());
        pstmt.setInt(++k, entity.getNumberOfRooms());
        pstmt.setDate(++k, new Date(entity.getCheckIn().getTime()));
        pstmt.setDate(++k, new Date(entity.getCheckOut().getTime()));
        pstmt.setLong(++k, entity.getUserId());
        pstmt.setLong(++k, entity.getApartmentClassId());
        pstmt.setInt(++k, entity.getStatus().getValue());
        pstmt.setLong(++k, entity.getApartmentId());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, PreOrder entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_PRE_ORDER);
        setStatement(entity, pstmt);
        pstmt.setLong(10, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, PreOrder entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_PRE_ORDER, Statement.RETURN_GENERATED_KEYS);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<PreOrder> readAll(ResultSet rs) throws SQLException {
        List<PreOrder> result = new ArrayList<>();
        PreOrder preOrder = null;
        while (rs.next()) {
            preOrder = new PreOrder();
            preOrder.setId(rs.getLong("id"));
            preOrder.setNumberOfAdult(rs.getInt("number_of_adult"));
            preOrder.setNumberOfChild(rs.getInt("number_of_child"));
            preOrder.setNumberOfRooms(rs.getInt("number_of_rooms"));
            preOrder.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            preOrder.setCheckIn(rs.getDate("check_in"));
            preOrder.setCheckOut(rs.getDate("check_out"));
            preOrder.setUserId(rs.getLong("account_id"));
            preOrder.setApartmentClassId(rs.getLong("apartment_class_id"));
            preOrder.setStatus(PreOrderStatus.getPreOrderStatusByValue(rs.getInt("status")));
            preOrder.setApartmentId(rs.getLong("apartment_id"));
            result.add(preOrder);
        }
        return result;
    }
}
