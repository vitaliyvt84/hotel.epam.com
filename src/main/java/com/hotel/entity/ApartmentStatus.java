package com.hotel.entity;

public enum ApartmentStatus {
    AVAILABLE(0), NOT_AVAILABLE(1);

    private Integer value;

    ApartmentStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static final ApartmentStatus getApartmentStatusByValue(Integer value) {
        for (ApartmentStatus status : ApartmentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
