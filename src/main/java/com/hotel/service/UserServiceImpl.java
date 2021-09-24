package com.hotel.service;

import com.hotel.exceptions.DBException;
import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.UserDTO;
import com.hotel.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<Long, UserDTO> {
    private Dao<Long, User> userDao;
    private static UserServiceImpl instance;

    private UserServiceImpl() {
        userDao = DaoFactory.getInstance().getUserDao();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreateTime(user.getCreateTime());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setRoleId(user.getRoleId());
        return userDTO;
    }

    private User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setRoleId(userDTO.getRoleId());
        return user;
    }

    @Override
    public List<UserDTO> getAll() throws DBException {
        List<User> users = userDao.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(mapUserToUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return userDao.countOfRows();
    }

    @Override
    public List<UserDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<User> users = userDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(mapUserToUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getById(Long id) throws DBException {
        User user = userDao.getById(id);
        UserDTO userDTO = null;
        if (user != null) {
            userDTO = mapUserToUserDTO(user);
        }
        return userDTO;
    }

    public UserDTO getByLogin(String value) throws DBException {
        List<User> users = userDao.getAllByValueFromColumn("login", value);
        UserDTO userDTO = null;
        if (users.size() > 0) {
            userDTO = mapUserToUserDTO(users.get(0));
        }
        return userDTO;
    }
    public UserDTO getByEmail(String value) throws DBException {
        List<User> users = userDao.getAllByValueFromColumn("email", value);
        UserDTO userDTO = null;
        if (users.size() > 0) {
            userDTO = mapUserToUserDTO(users.get(0));
        }
        return userDTO;
    }

    @Override
    public long save(UserDTO userDTO) throws DBException {
        User user = mapUserDTOToUser(userDTO);
        return userDao.save(user);
    }

    @Override
    public int delete(Long id) throws DBException {
        return userDao.delete(id);
    }

    @Override
    public int update(UserDTO userDTO) throws DBException {
        User user = mapUserDTOToUser(userDTO);
        return userDao.update(user);
    }
}
