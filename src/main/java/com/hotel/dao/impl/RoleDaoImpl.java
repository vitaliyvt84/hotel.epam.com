package com.hotel.dao.impl;

import com.hotel.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends CrudDAO<Role> {
    public RoleDaoImpl() {
        super("role");
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection con, Role entity) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection con, Role entity) throws SQLException {
        return null;
    }

    @Override
    protected List<Role> readAll(ResultSet rs) throws SQLException {
        List<Role> result = new ArrayList<>();
        Role role = null;
        while (rs.next()) {
            role = new Role();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            result.add(role);
        }
        return result;
    }
}
