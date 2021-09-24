package com.hotel.entity;

public enum BookingStatus {
    EMPTY(0), BOOKED(1), OCCUPIED(2), NOT_AVAILABLE(3);

    private Integer value;

    BookingStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static final BookingStatus getBookingStatusByValue(Integer value) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
