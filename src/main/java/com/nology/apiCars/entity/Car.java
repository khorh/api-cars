package com.nology.apiCars.entity;

public class Car {
    // Properties
    private int id;
    private String brand;
    private String type;
    private int yearLaunched;

    // Constructor
    public Car(int id, String group, String brand, String type, int yearLaunched) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.yearLaunched = yearLaunched;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearLaunched() {
        return yearLaunched;
    }

    public void setYearLaunched(int yearLaunched) {
        this.yearLaunched = yearLaunched;
    }
}
