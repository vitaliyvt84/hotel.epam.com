package com.hotel.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Booking extends Entity<Long> implements Serializable {
    private Date dateIn;
    private Date dateOut;
    private LocalDateTime createTime;
    private BookingStatus status;
    private Double price;
    private Integer numberOfAdult;
    private Integer numberOfChild;
    private Integer numberOfRooms;
    private Long userId;
    private Long apartmentId;

    public Booking() {
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfAdult() {
        return numberOfAdult;
    }

    public void setNumberOfAdult(Integer numberOfAdult) {
        this.numberOfAdult = numberOfAdult;
    }

    public Integer getNumberOfChild() {
        return numberOfChild;
    }

    public void setNumberOfChild(Integer numberOfChild) {
        this.numberOfChild = numberOfChild;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", createTime=" + createTime +
                ", status=" + status +
                ", price=" + price +
                ", numberOfAdult=" + numberOfAdult +
                ", numberOfChild=" + numberOfChild +
                ", numberOfRooms=" + numberOfRooms +
                ", userId=" + userId +
                ", apartmentId=" + apartmentId +
                '}';
    }
}
