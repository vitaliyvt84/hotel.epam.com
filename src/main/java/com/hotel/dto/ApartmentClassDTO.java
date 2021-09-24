package com.hotel.dto;

import com.hotel.entity.Entity;

public class ApartmentClassDTO extends Entity<Long> {
    private String name;
    private String description;

    public ApartmentClassDTO() {
    }

    public ApartmentClassDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
