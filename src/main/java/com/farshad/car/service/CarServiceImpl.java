package com.farshad.car.service;

import com.farshad.car.model.Car;
import com.farshad.car.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
@AllArgsConstructor
public class CarServiceImpl implements  CarService{
    private CarRepository carRepository;
    @Override
    public Mono<Car> save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Flux<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Mono<List<String>> getCarsVinByPolygonId(String polygonId) {
        return carRepository.findByPolygonId(polygonId).map(Car::getVin).collectList();
    }
}
