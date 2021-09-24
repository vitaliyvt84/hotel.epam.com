package com.hotel.entity;

import java.io.Serializable;

public class ApartmentClass extends Entity<Long> implements Serializable {
    private String name;
    private String description;

    public ApartmentClass() {
    }

    public ApartmentClass(String name, String description) {
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

    @Override
    public String toString() {
        return "ApartmentClass{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
