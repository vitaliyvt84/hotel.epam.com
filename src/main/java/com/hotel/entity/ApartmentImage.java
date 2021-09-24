package com.hotel.entity;

import java.io.Serializable;

public class ApartmentImage extends Entity<Long> implements Serializable {
    private String imageURL;
    private Integer imageType;
    private Long apartmentId;

    public ApartmentImage() {
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }
}
