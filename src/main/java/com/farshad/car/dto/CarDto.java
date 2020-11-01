package com.farshad.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long        id;
    private String      vin;
    private String      locationId;
    private String      numberPlate;
    private Position position;
    private Double      fuel;
    private String      model;
}
