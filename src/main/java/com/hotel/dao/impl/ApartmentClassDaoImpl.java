package com.hotel.dao.impl;

import com.hotel.entity.ApartmentClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.INSERT_APARTMENT_CLASS;
import static com.hotel.dao.SQLConstants.UPDATE_APARTMENT_CLASS;

public class ApartmentClassDaoImpl extends CrudDAO<ApartmentClass> {
    public ApartmentClassDaoImpl() {
        super("apartment_class");
    }

    private void setStatement(ApartmentClass entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setString(++k, entity.getName());
        pstmt.setString(++k, entity.getDescription());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, ApartmentClass entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_APARTMENT_CLASS);
        setStatement(entity, pstmt);
        pstmt.setLong(3, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, ApartmentClass entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_APARTMENT_CLASS, Statement.RETURN_GENERATED_KEYS);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<ApartmentClass> readAll(ResultSet rs) throws SQLException {
        List<ApartmentClass> result = new ArrayList<>();
        ApartmentClass apartmentC = null;
        while (rs.next()) {
            apartmentC = new ApartmentClass();
            apartmentC.setId(rs.getLong("id"));
            apartmentC.setName(rs.getString("name"));
            apartmentC.setDescription(rs.getString("description"));
            result.add(apartmentC);
        }
        return result;
    }
}
