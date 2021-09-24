package com.hotel.service;

import com.hotel.dao.Dao;
import com.hotel.dao.DaoFactory;
import com.hotel.dto.ApartmentImageDTO;
import com.hotel.entity.ApartmentImage;
import com.hotel.exceptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class ApartmentImageServiceImpl implements Service<Long, ApartmentImageDTO> {
    private Dao<Long, ApartmentImage> apartmentImageDao;
    private static ApartmentImageServiceImpl instance;

    private ApartmentImageServiceImpl() {
        apartmentImageDao = DaoFactory.getInstance().getApartmentImageDao();
    }

    public static synchronized ApartmentImageServiceImpl getInstance() {
        if (instance == null) {
            instance = new ApartmentImageServiceImpl();
        }
        return instance;
    }

    private ApartmentImageDTO mapApartmentImageToApartmentImageDTO(ApartmentImage apartmentImage) {
        ApartmentImageDTO apartmentImageDTO = new ApartmentImageDTO();
        apartmentImageDTO.setId(apartmentImage.getId());
        apartmentImageDTO.setApartmentId(apartmentImage.getApartmentId());
        apartmentImageDTO.setImageURL(apartmentImage.getImageURL());
        apartmentImageDTO.setImageType(apartmentImage.getImageType());
        return apartmentImageDTO;
    }

    private ApartmentImage mapApartmentImageDTOToApartmentImage(ApartmentImageDTO apartmentImageDTO) {
        ApartmentImage apartmentImage = new ApartmentImage();
        apartmentImage.setId(apartmentImageDTO.getId());
        apartmentImage.setApartmentId(apartmentImageDTO.getApartmentId());
        apartmentImage.setImageURL(apartmentImageDTO.getImageURL());
        apartmentImage.setImageType(apartmentImageDTO.getImageType());
        return apartmentImage;
    }

    @Override
    public List<ApartmentImageDTO> getAll() throws DBException {
        List<ApartmentImage> apartmentImageList = apartmentImageDao.getAll();
        List<ApartmentImageDTO> apartmentImageDTOs = new ArrayList<>();
        for (ApartmentImage apartmentImage : apartmentImageList) {
            apartmentImageDTOs.add(mapApartmentImageToApartmentImageDTO(apartmentImage));
        }
        return apartmentImageDTOs;
    }

    @Override
    public ApartmentImageDTO getById(Long id) throws DBException {
        ApartmentImage apartmentImage = apartmentImageDao.getById(id);
        ApartmentImageDTO apartmentImageDTO = null;
        if (apartmentImage != null) {
            apartmentImageDTO = mapApartmentImageToApartmentImageDTO(apartmentImage);
        }
        return apartmentImageDTO;
    }

    @Override
    public List<ApartmentImageDTO> getAllOrderByColumnOffsetNumberOfRecords(String columnName, Integer offset, Integer numberOfRecords) throws DBException {
        List<ApartmentImage> apartmentImageList = apartmentImageDao.getAllOrderByColumnOffsetNumberOfRecords(columnName, offset, numberOfRecords);
        List<ApartmentImageDTO> apartmentImageDTOs = new ArrayList<>();
        for (ApartmentImage apartmentImage : apartmentImageList) {
            apartmentImageDTOs.add(mapApartmentImageToApartmentImageDTO(apartmentImage));
        }
        return apartmentImageDTOs;
    }

    public List<ApartmentImageDTO> getAllByApartmentId(Long id) throws DBException {
        List<ApartmentImage> apartmentImages = apartmentImageDao.getAllByValueFromColumn("apartment_id", String.valueOf(id));
        List<ApartmentImageDTO> apartmentImageDTOs = new ArrayList<>();
        for (ApartmentImage apartmentImage : apartmentImages) {
            apartmentImageDTOs.add(mapApartmentImageToApartmentImageDTO(apartmentImage));
        }
        return apartmentImageDTOs;
    }

    @Override
    public long countOfRows() throws DBException {
        return apartmentImageDao.countOfRows();
    }

    @Override
    public long save(ApartmentImageDTO entity) throws DBException {
        ApartmentImage apartmentImage = mapApartmentImageDTOToApartmentImage(entity);
        return apartmentImageDao.save(apartmentImage);
    }

    @Override
    public int delete(Long id) throws DBException {
        return apartmentImageDao.delete(id);
    }

    @Override
    public int update(ApartmentImageDTO entity) throws DBException {
        ApartmentImage apartmentImage = mapApartmentImageDTOToApartmentImage(entity);
        return apartmentImageDao.update(apartmentImage);
    }
}
