package com.farshad.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarsInLocationResponseDto {
    private List<CarDto> carDtos;
}
