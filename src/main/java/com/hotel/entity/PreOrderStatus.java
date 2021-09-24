package com.hotel.entity;

public enum PreOrderStatus {
    NEW(0), PROCESSED(1), COMPLETED(2), CANCELED(3);

    private Integer value;

    PreOrderStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static final PreOrderStatus getPreOrderStatusByValue(Integer value) {
        for (PreOrderStatus status : PreOrderStatus.values()) {
            if(status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
