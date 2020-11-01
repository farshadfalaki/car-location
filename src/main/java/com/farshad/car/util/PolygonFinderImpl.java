package com.farshad.car.util;

import com.farshad.car.dto.Position;
import com.farshad.car.model.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PolygonFinderImpl implements PolygonFinder{
    @Override
    public Optional<Polygon> findPolygonFromLocation(List<Polygon> polygonList, Position position) {
        return polygonList.stream().filter(polygon->isInPolygon(polygon,position)).findFirst();
    }

    boolean isInPolygon(Polygon polygon, Position position){
        Point point = new Point(position.getLongitude(),position.getLatitude());
        Point[] p = polygon.getGeometry().getCoordinates().get(0).stream().map(e->new Point(e.get(0),e.get(1))).skip(1).toArray(Point[]::new);
        return CoordinationUtil.isInside(p,p.length,point);
    }
}
