package com.farshad.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimedOption {
    private String key;
    private List<List<Double>> changesOverTime = null;
}
