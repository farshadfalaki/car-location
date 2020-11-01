package com.farshad.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolygonWithCarsDto {
    private String id;
    private String updatedAt;
    private String createdAt;
    private String name;
    private String cityId;
    private String legacyId;
    private String type;
    private List<String> vins;
}
