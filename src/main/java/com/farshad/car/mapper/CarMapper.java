package com.farshad.car.mapper;

import com.farshad.car.dto.CarDto;
import com.farshad.car.model.Car;

public interface CarMapper {
    Car toModel(CarDto carDto, String polygonId);
}
