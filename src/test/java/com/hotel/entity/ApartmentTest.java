package com.hotel.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApartmentTest {
    private Apartment apartment;

    @Before
    public void setUp() throws Exception {
        apartment = new Apartment();
        apartment.setId(5L);
        apartment.setNumber(23);
        apartment.setName("Standard Room");
        apartment.setPrice(500.0);
        apartment.setMaxCountOfAdult(2);
        apartment.setMaxCountOfChild(2);
        apartment.setStatus(ApartmentStatus.AVAILABLE);
        apartment.setCountOfRoom(3);
        apartment.setDescription("This is standard room");
        apartment.setNumberOfBed(2);
        apartment.setApartmentClassId(8L);
    }

    @Test
    public void shouldReturnCorrectApartmentId() {
        Long expected = 5L;
        assertEquals(expected, apartment.getId());
    }

    @Test
    public void shouldReturnCorrectNumberOfApartment() {
        Integer expected = 23;
        assertEquals(expected, apartment.getNumber());
    }

    @Test
    public void setNumberShouldWorkCorrectly() {
        Integer expected = 55;
        apartment.setNumber(55);
        assertEquals(expected, apartment.getNumber());
    }

    @Test
    public void shouldReturnCorrectApartmentName() {
        assertEquals("Standard Room", apartment.getName());
    }

    @Test
    public void setNameShouldWorkCorrectly() {
        apartment.setName("Simple Room");
        assertEquals("Simple Room", apartment.getName());
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Double expected = 500.0;
        assertEquals(expected, apartment.getPrice());
    }

    @Test
    public void setPriceShouldWorkCorrectly() {
        Double expected = 434.0;
        apartment.setPrice(434.0);
        assertEquals(expected, apartment.getPrice());
    }

    @Test
    public void shouldReturnCorrectMaxCountOfAdult() {
        Integer expected = 2;
        assertEquals(expected, apartment.getMaxCountOfAdult());
    }

    @Test
    public void setMaxCountOfAdultShouldWorkCorrectly() {
        Integer expected = 3;
        apartment.setMaxCountOfChild(3);
        assertEquals(expected, apartment.getMaxCountOfChild());
    }

    @Test
    public void shouldReturnCorrectMaxCountOfChild() {
        Integer expected = 2;
        assertEquals(expected, apartment.getMaxCountOfChild());
    }

    @Test
    public void setMaxCountOfChildShouldWorkCorrectly() {
        Integer expected = 5;
        apartment.setMaxCountOfChild(5);
        assertEquals(expected, apartment.getMaxCountOfChild());
    }

    @Test
    public void shouldReturnCorrectStatus() {
        assertEquals(ApartmentStatus.AVAILABLE, apartment.getStatus());
    }

    @Test
    public void setStatusShouldWorkCorrectly() {
        apartment.setStatus(ApartmentStatus.NOT_AVAILABLE);
        assertEquals(ApartmentStatus.NOT_AVAILABLE, apartment.getStatus());
    }

    @Test
    public void shouldReturnCorrectCountOfRoom() {
        Integer expected = 3;
        assertEquals(expected, apartment.getCountOfRoom());
    }

    @Test
    public void setCountOfRoomShouldWorkCorrectly() {
        Integer expected = 6;
        apartment.setCountOfRoom(6);
        assertEquals(expected, apartment.getCountOfRoom());
    }

    @Test
    public void shouldReturnCorrectDescription() {
        assertEquals("This is standard room", apartment.getDescription());
    }

    @Test
    public void setDescriptionShouldWorkCorrectly() {
        apartment.setDescription("Double room");
        assertEquals("Double room", apartment.getDescription());
    }

    @Test
    public void shouldReturnCorrectNumberOfBed() {
        Integer expected = 2;
        assertEquals(expected, apartment.getNumberOfBed());
    }

    @Test
    public void setNumberOfBedShouldWorkCorrectly() {
        Integer expected = 6;
        apartment.setNumberOfBed(6);
        assertEquals(expected, apartment.getNumberOfBed());
    }

    @Test
    public void shouldReturnCorrectApartmentClassId() {
        Long expected = 8L;
        assertEquals(expected, apartment.getApartmentClassId());
    }

    @Test
    public void setApartmentClassIdShouldWorkCorrectly() {
        Long expected = 6L;
        apartment.setApartmentClassId(6L);
        assertEquals(expected, apartment.getApartmentClassId());
    }

    @Test
    public void methodToStringShouldWorkCorrectly() {
        String expected = "Apartment{number=23, name='Standard Room', price=500.0, maxCountOfAdult=2, " +
                "maxCountOfChild=2, status=AVAILABLE, countOfRoom=3, description='This is standard room', " +
                "numberOfBed=2, apartmentClassId=8}";
        assertEquals(expected, apartment.toString());
    }


}