package com.farshad.car.service;

import com.farshad.car.model.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CarService {
    Mono<Car> save(Car car);
    Flux<Car> getAllCars();
    Mono<List<String>> getCarsVinByPolygonId(String polygonId);
}
