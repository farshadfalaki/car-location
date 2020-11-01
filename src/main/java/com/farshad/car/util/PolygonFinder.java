package com.farshad.car.util;

import com.farshad.car.dto.Position;
import com.farshad.car.model.Polygon;

import java.util.List;
import java.util.Optional;

public interface PolygonFinder {
    Optional<Polygon> findPolygonFromLocation(List<Polygon> polygonList, Position position);
}
