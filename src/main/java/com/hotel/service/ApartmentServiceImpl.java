package com.hotel.service;

import com.hotel.dao.ApartmentDao;
import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.ApartmentDTO;
import com.hotel.entity.Apartment;
import com.hotel.exceptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class ApartmentServiceImpl implements Service<Long, ApartmentDTO> {
    private ApartmentDao<Long, Apartment> apartmentDao;
    private static ApartmentServiceImpl instance;

    private ApartmentServiceImpl() {
        apartmentDao = DaoFactory.getInstance().getApartmentDao();
    }

    public static synchronized ApartmentServiceImpl getInstance() {
        if (instance == null) {
            instance = new ApartmentServiceImpl();
        }
        return instance;
    }

    private ApartmentDTO mapApartmentToApartmentDTO(Apartment apartment) {
        ApartmentDTO apartmentDTO = new ApartmentDTO();
        apartmentDTO.setId(apartment.getId());
        apartmentDTO.setNumber(apartment.getNumber());
        apartmentDTO.setName(apartment.getName());
        apartmentDTO.setPrice(apartment.getPrice());
        apartmentDTO.setMaxCountOfAdult(apartment.getMaxCountOfAdult());
        apartmentDTO.setMaxCountOfChild(apartment.getMaxCountOfChild());
        apartmentDTO.setStatus(apartment.getStatus());
        apartmentDTO.setCountOfRoom(apartment.getCountOfRoom());
        apartmentDTO.setDescription(apartment.getDescription());
        apartmentDTO.setNumberOfBed(apartment.getNumberOfBed());
        apartmentDTO.setApartmentClassId(apartment.getApartmentClassId());
        return apartmentDTO;
    }

    private Apartment mapApartmentDTOToApartment(ApartmentDTO apartmentDTO) {
        Apartment apartment = new Apartment();
        apartment.setId(apartmentDTO.getId());
        apartment.setNumber(apartmentDTO.getNumber());
        apartment.setName(apartmentDTO.getName());
        apartment.setPrice(apartmentDTO.getPrice());
        apartment.setMaxCountOfAdult(apartmentDTO.getMaxCountOfAdult());
        apartment.setMaxCountOfChild(apartmentDTO.getMaxCountOfChild());
        apartment.setStatus(apartmentDTO.getStatus());
        apartment.setCountOfRoom(apartmentDTO.getCountOfRoom());
        apartment.setDescription(apartmentDTO.getDescription());
        apartment.setNumberOfBed(apartmentDTO.getNumberOfBed());
        apartment.setApartmentClassId(apartmentDTO.getApartmentClassId());
        return apartment;
    }

    @Override
    public List<ApartmentDTO> getAll() throws DBException {
        List<Apartment> apartments = apartmentDao.getAll();
        List<ApartmentDTO> apartmentDTOs = new ArrayList<>();
        for (Apartment apartment : apartments) {
            apartmentDTOs.add(mapApartmentToApartmentDTO(apartment));
        }
        return apartmentDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return apartmentDao.countOfRows();
    }

    @Override
    public List<ApartmentDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<Apartment> apartments = apartmentDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<ApartmentDTO> apartmentDTOs = new ArrayList<>();
        for (Apartment apartment : apartments) {
            apartmentDTOs.add(mapApartmentToApartmentDTO(apartment));
        }
        return apartmentDTOs;
    }

    public List<ApartmentDTO> getAllWhereOrderByColumnOffsetNumOfRec(String orderByColumnName, Integer offset,
                                                                  Integer numberOfRecords, Integer adultNum,
                                                                  Integer childNum, Integer roomNum) throws DBException {
        List<Apartment> apartments = apartmentDao.getAllWhereOrderByColumnOffsetNumOfRec(orderByColumnName, offset, numberOfRecords, adultNum, childNum, roomNum);
        List<ApartmentDTO> apartmentDTOs = new ArrayList<>();
        for (Apartment apartment : apartments) {
            apartmentDTOs.add(mapApartmentToApartmentDTO(apartment));
        }
        return apartmentDTOs;
    }

    public List<ApartmentDTO> getAllWhereThreeCondition(Integer adultNum, Integer childNum, Integer roomNum) throws DBException {
        List<Apartment> apartments = apartmentDao.getAllWhereThreeCondition(adultNum, childNum, roomNum);
        List<ApartmentDTO> apartmentDTOs = new ArrayList<>();
        for (Apartment apartment : apartments) {
            apartmentDTOs.add(mapApartmentToApartmentDTO(apartment));
        }
        return apartmentDTOs;
    }

    public long countOfRowsWhere(Integer adultNum, Integer childNum, Integer roomNum) throws DBException {
        return apartmentDao.countOfRowsWhere(adultNum, childNum, roomNum);
    }

    @Override
    public ApartmentDTO getById(Long id) throws DBException {
        Apartment apartment = apartmentDao.getById(id);
        ApartmentDTO apartmentDTO = mapApartmentToApartmentDTO(apartment);
        return apartmentDTO;
    }

    @Override
    public long save(ApartmentDTO apartmentDTO) throws DBException {
        Apartment apartment = mapApartmentDTOToApartment(apartmentDTO);
        return apartmentDao.save(apartment);
    }

    @Override
    public int delete(Long id) throws DBException {
        return apartmentDao.delete(id);
    }

    @Override
    public int update(ApartmentDTO apartmentDTO) throws DBException {
        Apartment apartment = mapApartmentDTOToApartment(apartmentDTO);
        return apartmentDao.update(apartment);
    }
}
