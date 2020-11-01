package com.farshad.car.controller;

import com.farshad.car.dto.PolygonWithCarsDto;
import com.farshad.car.mapper.PolygonMapper;
import com.farshad.car.model.Car;
import com.farshad.car.model.Polygon;
import com.farshad.car.service.CarService;
import com.farshad.car.service.PolygonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CarController {
    private CarService carService;
    private PolygonService polygonService;
    private PolygonMapper polygonMapper;

    @GetMapping("/cars")
    public Flux<Car> getAllCars(){
        log.info("Endpoint cars is called");
        return carService.getAllCars();
    }

    @GetMapping("/polygons")
    public Flux<PolygonWithCarsDto> getPolygons(){
        log.info("Endpoint polygons is called");
        return polygonService.getAll()
                .flatMap(this::collectCarVinsInPolygon)
                .onErrorResume(throwable -> {
                        log.warn("Error while listing polygons : {}",throwable);
                        return Flux.empty();
                });
    }

    public Mono<PolygonWithCarsDto> collectCarVinsInPolygon(Polygon polygon){
        Mono<List<String>> carsVinListMono = carService.getCarsVinByPolygonId(polygon.getId());
        return carsVinListMono.map(vinList->polygonMapper.toDto(polygon,vinList));
    }
}
