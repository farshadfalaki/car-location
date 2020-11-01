package com.farshad.car.repository;

import com.farshad.car.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CarRepository extends ReactiveMongoRepository<Car,Long> {
    Flux<Car> findByPolygonId(String polygonId);

}
