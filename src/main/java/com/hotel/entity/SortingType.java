package com.hotel.entity;

public enum SortingType {
    APC_ID("apartment_class_id"), PR("price"), PRD("price DESC"), MCA("max_count_adult"), NM("name"), ST("status");

    private String value;

    SortingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final SortingType getSortingTypeByValue(String value) {
        for (SortingType type : SortingType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
