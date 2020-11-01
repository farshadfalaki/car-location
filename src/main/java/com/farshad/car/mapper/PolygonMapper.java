package com.farshad.car.mapper;

import com.farshad.car.dto.PolygonWithCarsDto;
import com.farshad.car.model.Polygon;

import java.util.List;
public interface PolygonMapper {
    PolygonWithCarsDto toDto(Polygon polygon, List<String> vins);
}
