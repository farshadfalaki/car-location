package com.farshad.car.mapper;

import com.farshad.car.dto.CarDto;
import com.farshad.car.model.Car;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CarMapperImpl implements CarMapper{
    public Car toModel(CarDto carDto, String polygonId){
        return new Car(carDto.getId(), carDto.getVin(), carDto.getNumberPlate(), carDto.getPosition(), carDto.getFuel(),
                carDto.getModel(),polygonId, Instant.now());
    }
}
