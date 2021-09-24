package com.hotel.service;

import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.PreOrderDTO;
import com.hotel.entity.PreOrder;
import com.hotel.exceptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class PreOrderServiceImpl implements Service<Long, PreOrderDTO> {
    private Dao<Long, PreOrder> preOrderDao;
    private static PreOrderServiceImpl instance;

    private PreOrderServiceImpl() {
        preOrderDao = DaoFactory.getInstance().getPreOrderDao();
    }

    public static synchronized PreOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new PreOrderServiceImpl();
        }
        return instance;
    }

    private PreOrderDTO mapPreOrderToPreOrderDTO(PreOrder preOrder) {
        PreOrderDTO preOrderDTO = new PreOrderDTO();
        preOrderDTO.setId(preOrder.getId());
        preOrderDTO.setNumberOfAdult(preOrder.getNumberOfAdult());
        preOrderDTO.setNumberOfChild(preOrder.getNumberOfChild());
        preOrderDTO.setNumberOfRooms(preOrder.getNumberOfRooms());
        preOrderDTO.setCreateTime(preOrder.getCreateTime());
        preOrderDTO.setCheckIn(preOrder.getCheckIn());
        preOrderDTO.setCheckOut(preOrder.getCheckOut());
        preOrderDTO.setUserId(preOrder.getUserId());
        preOrderDTO.setApartmentClassId(preOrder.getApartmentClassId());
        preOrderDTO.setStatus(preOrder.getStatus());
        preOrderDTO.setApartmentId(preOrder.getApartmentId());
        return preOrderDTO;
    }
    private PreOrder mapPreOrderDTOToPreOrder(PreOrderDTO preOrderDTO) {
        PreOrder preOrder = new PreOrder();
        preOrder.setId(preOrderDTO.getId());
        preOrder.setNumberOfAdult(preOrderDTO.getNumberOfAdult());
        preOrder.setNumberOfChild(preOrderDTO.getNumberOfChild());
        preOrder.setNumberOfRooms(preOrderDTO.getNumberOfRooms());
        preOrder.setCheckIn(preOrderDTO.getCheckIn());
        preOrder.setCheckOut(preOrderDTO.getCheckOut());
        preOrder.setUserId(preOrderDTO.getUserId());
        preOrder.setApartmentClassId(preOrderDTO.getApartmentClassId());
        preOrder.setStatus(preOrderDTO.getStatus());
        preOrder.setApartmentId(preOrderDTO.getApartmentId());
        return preOrder;
    }

    @Override
    public List<PreOrderDTO> getAll() throws DBException {
        List<PreOrder> preOrders = preOrderDao.getAll();
        List<PreOrderDTO> preOrderDTOs = new ArrayList<>();
        for (PreOrder preOrder : preOrders) {
            preOrderDTOs.add(mapPreOrderToPreOrderDTO(preOrder));
        }
        return preOrderDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return preOrderDao.countOfRows();
    }

    @Override
    public List<PreOrderDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<PreOrder> preOrders = preOrderDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<PreOrderDTO> preOrderDTOs = new ArrayList<>();
        for (PreOrder preOrder : preOrders) {
            preOrderDTOs.add(mapPreOrderToPreOrderDTO(preOrder));
        }
        return preOrderDTOs;
    }

    @Override
    public PreOrderDTO getById(Long id) throws DBException {
        PreOrder preOrder = preOrderDao.getById(id);
        PreOrderDTO preOrderDTO = mapPreOrderToPreOrderDTO(preOrder);
        return preOrderDTO;
    }

    public List<PreOrderDTO> getByUserId(Long id) throws DBException {
        List<PreOrder> preOrders = preOrderDao.getAllByValueFromColumn("account_id", String.valueOf(id));
        List<PreOrderDTO> preOrderDTOs = new ArrayList<>();
        for (PreOrder preOrder : preOrders) {
            preOrderDTOs.add(mapPreOrderToPreOrderDTO(preOrder));
        }
        return preOrderDTOs;
    }

    @Override
    public long save(PreOrderDTO preOrderDTO) throws DBException {
        PreOrder preOrder = mapPreOrderDTOToPreOrder(preOrderDTO);
        return preOrderDao.save(preOrder);
    }

    @Override
    public int delete(Long id) throws DBException {
        return preOrderDao.delete(id);
    }

    @Override
    public int update(PreOrderDTO preOrderDTO) throws DBException {
        PreOrder preOrder = mapPreOrderDTOToPreOrder(preOrderDTO);
        return preOrderDao.update(preOrder);
    }
}
