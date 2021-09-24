package com.hotel.dto;

import com.hotel.entity.Entity;
import com.hotel.entity.PreOrderStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class PreOrderDTO extends Entity<Long> {
    private Integer numberOfAdult;
    private Integer numberOfChild;
    private Integer numberOfRooms;
    private LocalDateTime createTime;
    private Date checkIn;
    private Date checkOut;
    private Long userId;
    private Long apartmentClassId;
    private PreOrderStatus status;
    private Long apartmentId;

    public PreOrderDTO() {
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApartmentClassId() {
        return apartmentClassId;
    }

    public void setApartmentClassId(Long apartmentClassId) {
        this.apartmentClassId = apartmentClassId;
    }

    public PreOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PreOrderStatus status) {
        this.status = status;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }
}