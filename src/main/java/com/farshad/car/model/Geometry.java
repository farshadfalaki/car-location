package com.farshad.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry {
        private String type;
        private List<List<List<Double>>> coordinates = null;

}
