package com.hotel.service;

import com.hotel.exceptions.DBException;
import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.RoleDTO;
import com.hotel.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements Service<Long, RoleDTO> {
    private static Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private Dao<Long, Role> roleDao;
    private static RoleServiceImpl instance;

    private RoleServiceImpl() {
        roleDao = DaoFactory.getInstance().getRoleDao();
    }

    public static synchronized RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    private RoleDTO mapRoleToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO(role.getName());
        roleDTO.setId(role.getId());
        return roleDTO;
    }

    @Override
    public List<RoleDTO> getAll() throws DBException {
        List<Role> roles = roleDao.getAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(mapRoleToRoleDTO(role));
        }
        return roleDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return roleDao.countOfRows();
    }

    @Override
    public List<RoleDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        return null;
    }

    @Override
    public RoleDTO getById(Long id) throws DBException {
        Role role = roleDao.getById(id);
        RoleDTO roleDTO = null;
        if (role != null) {
            roleDTO = mapRoleToRoleDTO(role);
        }
        return roleDTO;
    }

    @Override
    public long save(RoleDTO entity) throws DBException {
        logger.warn("Try to save role");
        throw new UnsupportedOperationException("Cannot save role");
    }

    @Override
    public int delete(Long id) throws DBException {
        logger.warn("Try to delete role");
        throw new UnsupportedOperationException("Cannot delete role");
    }

    @Override
    public int update(RoleDTO entity) {
        logger.warn("Try to update role");
        throw new UnsupportedOperationException("Cannot update role");
    }
}
