package com.hotel.entity;

import java.io.Serializable;

public class Apartment extends Entity<Long> implements Serializable {
    private Integer number;
    private String name;
    private Double price;
    private Integer maxCountOfAdult;
    private Integer maxCountOfChild;
    private ApartmentStatus status;
    private Integer countOfRoom;
    private String description;
    private Integer numberOfBed;
    private Long apartmentClassId;

    public Apartment() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxCountOfAdult() {
        return maxCountOfAdult;
    }

    public void setMaxCountOfAdult(Integer maxCountOfAdult) {
        this.maxCountOfAdult = maxCountOfAdult;
    }

    public Integer getMaxCountOfChild() {
        return maxCountOfChild;
    }

    public void setMaxCountOfChild(Integer maxCountOfChild) {
        this.maxCountOfChild = maxCountOfChild;
    }

    public ApartmentStatus getStatus() {
        return status;
    }

    public void setStatus(ApartmentStatus status) {
        this.status = status;
    }

    public Integer getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(Integer countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Integer numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public Long getApartmentClassId() {
        return apartmentClassId;
    }

    public void setApartmentClassId(Long apartmentClassId) {
        this.apartmentClassId = apartmentClassId;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", maxCountOfAdult=" + maxCountOfAdult +
                ", maxCountOfChild=" + maxCountOfChild +
                ", status=" + status +
                ", countOfRoom=" + countOfRoom +
                ", description='" + description + '\'' +
                ", numberOfBed=" + numberOfBed +
                ", apartmentClassId=" + apartmentClassId +
                '}';
    }
}
