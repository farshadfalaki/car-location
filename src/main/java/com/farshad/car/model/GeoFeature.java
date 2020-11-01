package com.farshad.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoFeature {
    private String name;
    private GeometryPoint geometry;
}
