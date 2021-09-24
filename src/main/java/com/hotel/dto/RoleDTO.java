package com.hotel.dto;

import com.hotel.entity.Entity;

public class RoleDTO extends Entity<Long> {
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
