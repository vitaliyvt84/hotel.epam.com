package com.hotel.dao.impl;

import com.hotel.entity.ApartmentImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.INSERT_APARTMENT_IMAGE;
import static com.hotel.dao.SQLConstants.UPDATE_APARTMENT_IMAGE;

public class ApartmentImageDaoImpl extends CrudDAO<ApartmentImage> {
    public ApartmentImageDaoImpl() {
        super("apartment_image");
    }

    private void setStatement(ApartmentImage entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setLong(++k, entity.getApartmentId());
        pstmt.setString(++k, entity.getImageURL());
        pstmt.setInt(++k, entity.getImageType());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, ApartmentImage entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_APARTMENT_IMAGE);
        setStatement(entity, pstmt);
        pstmt.setLong(4, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, ApartmentImage entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_APARTMENT_IMAGE);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<ApartmentImage> readAll(ResultSet rs) throws SQLException {
        List<ApartmentImage> result = new ArrayList<>();
        ApartmentImage apartmentI = null;
        while (rs.next()) {
            apartmentI = new ApartmentImage();
            apartmentI.setId(rs.getLong("id"));
            apartmentI.setApartmentId(rs.getLong("apartment_id"));
            apartmentI.setImageURL(rs.getString("image_url"));
            apartmentI.setImageType(rs.getInt("image_type"));
            result.add(apartmentI);
        }
        return result;
    }
}
