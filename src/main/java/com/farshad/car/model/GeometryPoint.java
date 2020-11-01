package com.farshad.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeometryPoint {
    private String type;
    private List<Double> coordinates = null;
}
