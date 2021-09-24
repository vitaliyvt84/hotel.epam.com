package com.hotel.dao.impl;

import com.hotel.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hotel.dao.SQLConstants.INSERT_ACCOUNT;
import static com.hotel.dao.SQLConstants.UPDATE_ACCOUNT;

public class AccountDaoImpl extends CrudDAO<User> {
    public AccountDaoImpl() {
        super("account");
    }

    private void setStatement(User entity, PreparedStatement pstmt) throws SQLException {
        int k = 0;
        pstmt.setString(++k, entity.getLogin());
        pstmt.setString(++k, entity.getEmail());
        pstmt.setString(++k, entity.getPassword());
        pstmt.setString(++k, entity.getName());
        pstmt.setString(++k, entity.getPhone());
        pstmt.setString(++k, entity.getAddress());
        pstmt.setLong(++k, entity.getRoleId());
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, User entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_ACCOUNT);
        setStatement(entity, pstmt);
        pstmt.setLong(8, entity.getId());
        return pstmt;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, User entity) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        setStatement(entity, pstmt);
        return pstmt;
    }

    @Override
    protected List<User> readAll(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        User user = null;
        while (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setAddress(rs.getString("address"));
            user.setRoleId(rs.getLong("role_id"));
            result.add(user);
        }
        return result;
    }
}
