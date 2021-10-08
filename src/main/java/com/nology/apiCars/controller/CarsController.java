package com.nology.apiCars.controller;

import com.nology.apiCars.entity.Car;
import com.nology.apiCars.repository.CarRepository;
import com.nology.apiCars.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarsController {
    private final CarRepository repository = new CarRepository();

    // CREATE a car
    @PostMapping("/cars")
    public ResponseEntity<Message> createCar(@RequestBody Car newCar) {
        try {
            if (newCar.getId() > 0 && newCar.getYearLaunched() >= 1886) {
                repository.getCarsPortfolio().add(newCar);
                Message successMessage = new Message("Successfully created new car");
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(successMessage);
            }
            throw new Exception();
        } catch (Exception e) {
            Message unsuccessfulMessage = new Message("Car input is incorrect");
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(unsuccessfulMessage);
        }
    }

    // READ all cars
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> displayCars() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.getCarsPortfolio());
    }

    // READ a car based on an id
    @GetMapping("/cars/{id}")
    public ResponseEntity<String> displayIndividualCar(@PathVariable int id) {
        String carInfo = "";
        for (Car individualCar : repository.getCarsPortfolio()) {
            if (individualCar.getId() == id) {
                carInfo = individualCar.getBrand() + " " + individualCar.getType() + " - " + individualCar.getYearLaunched();
            }
        }

        if (carInfo.length() == 0) {
            Message unsuccessfulMessage = new Message("Incorrect id, please try again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(unsuccessfulMessage.getText());
        }

        return ResponseEntity.status(HttpStatus.OK).body(carInfo);
    }

    // UPDATE a car based on an id
    @PutMapping("cars/{id}")
    public ResponseEntity<Message> updateIndividualCar(@PathVariable int id, @RequestBody Car amendCar) {
        try {
            for (Car individualCar : repository.getCarsPortfolio()) {
                if (individualCar.getId() == id) {
                    individualCar.setBrand(amendCar.getBrand());
                    individualCar.setType(amendCar.getType());
                    individualCar.setYearLaunched(amendCar.getYearLaunched());

                    repository.getCarsPortfolio().set(id, individualCar);

                    Message successMessage = new Message("Successfully updated car");
                    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
                }
            }
            throw new Exception();
        } catch (Exception e) {
            Message unsuccessfulMessage = new Message("Incorrect id, please try again");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(unsuccessfulMessage);
        }
    }

    // DELETE a car based on an id
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Message> deleteIndividualCar(@PathVariable int id) {
        try {
            for (Car individualCar : repository.getCarsPortfolio()) {
                if (individualCar.getId() == id) {
                    repository.getCarsPortfolio().remove(id);
                    Message successMessage = new Message("Successfully deleted car");
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(successMessage);
                }
            }
            throw new Exception();
        } catch (Exception e) {
            Message unsuccessfulMessage = new Message("Incorrect id, please try again");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(unsuccessfulMessage);
        }
    }
}
