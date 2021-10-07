package com.nology.apiCars.repository;

import com.nology.apiCars.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private List<Car> carsPortfolio = new ArrayList<>();

    public List<Car> getCarsPortfolio() {
        return carsPortfolio;
    }
}
