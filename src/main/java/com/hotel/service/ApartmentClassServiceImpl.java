package com.hotel.service;

import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.ApartmentClassDTO;
import com.hotel.entity.ApartmentClass;
import com.hotel.exceptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class ApartmentClassServiceImpl implements Service<Long, ApartmentClassDTO> {
    private Dao<Long, ApartmentClass> apartmentClassDao;
    private static ApartmentClassServiceImpl instance;

    private ApartmentClassServiceImpl() {
        apartmentClassDao = DaoFactory.getInstance().getApartmentClassDao();
    }

    public static synchronized ApartmentClassServiceImpl getInstance() {
        if (instance == null) {
            instance = new ApartmentClassServiceImpl();
        }
        return instance;
    }

    private ApartmentClassDTO mapApartmentClassToApartmentClassDTO(ApartmentClass apartClass) {
        ApartmentClassDTO apartClassDTO = new ApartmentClassDTO();
        apartClassDTO.setId(apartClass.getId());
        apartClassDTO.setName(apartClass.getName());
        apartClassDTO.setDescription(apartClass.getDescription());
        return apartClassDTO;
    }

    private ApartmentClass mapApartmentClassDTOToApartmentClass(ApartmentClassDTO apartClassDTO) {
        ApartmentClass apartClass = new ApartmentClass();
        apartClass.setId(apartClassDTO.getId());
        apartClass.setName(apartClassDTO.getName());
        apartClass.setDescription(apartClassDTO.getDescription());
        return apartClass;
    }

    @Override
    public List<ApartmentClassDTO> getAll() throws DBException {
        List<ApartmentClass> apartClassList = apartmentClassDao.getAll();
        List<ApartmentClassDTO> apartClassDTOs = new ArrayList<>();
        for (ApartmentClass apartmentClass : apartClassList) {
            apartClassDTOs.add(mapApartmentClassToApartmentClassDTO(apartmentClass));
        }
        return apartClassDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return apartmentClassDao.countOfRows();
    }

    @Override
    public List<ApartmentClassDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<ApartmentClass> apartClassList = apartmentClassDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<ApartmentClassDTO> apartClassDTOs = new ArrayList<>();
        for (ApartmentClass apartmentClass : apartClassList) {
            apartClassDTOs.add(mapApartmentClassToApartmentClassDTO(apartmentClass));
        }
        return apartClassDTOs;
    }

    @Override
    public ApartmentClassDTO getById(Long id) throws DBException {
        ApartmentClass apartClass = apartmentClassDao.getById(id);
        ApartmentClassDTO apartClassDTO = mapApartmentClassToApartmentClassDTO(apartClass);
        return apartClassDTO;
    }

    @Override
    public long save(ApartmentClassDTO apartClassDTO) throws DBException {
        ApartmentClass apartClass = mapApartmentClassDTOToApartmentClass(apartClassDTO);
        return apartmentClassDao.save(apartClass);
    }

    @Override
    public int delete(Long id) throws DBException {
        return apartmentClassDao.delete(id);
    }

    @Override
    public int update(ApartmentClassDTO apartClassDTO) throws DBException {
        ApartmentClass apartClass = mapApartmentClassDTOToApartmentClass(apartClassDTO);
        return apartmentClassDao.update(apartClass);
    }
}
