package com.farshad.car.util;

import com.farshad.car.dto.Position;
import com.farshad.car.model.Geometry;
import com.farshad.car.model.Polygon;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PolygonFinderImplTest {

    @Test
    public void findPolygonFromLocation() {
    }

    @Test
    public void isInPolygon() {
        PolygonFinderImpl polygonFinder = new PolygonFinderImpl();
        Position position = new Position(1d,2d);
        Polygon polygon = new Polygon();
        List<List<List<Double>>> coordinates = Arrays.asList(Arrays.asList(Arrays.asList(-2d,-1d),Arrays.asList(1d,-2d),Arrays.asList(3d,2d),Arrays.asList(0d,3d)));
        Geometry geometry = new Geometry("Polygon",coordinates);
        polygon.setGeometry(geometry);
        boolean expectedResult = true;
        boolean actualResult = polygonFinder.isInPolygon(polygon,position);
        assertEquals(expectedResult,actualResult);
    }


    @Test
    public void isInPolygon3() {
        PolygonFinderImpl polygonFinder = new PolygonFinderImpl();
        Position position = new Position(3d,3d);
        Polygon polygon = new Polygon();
        List<List<List<Double>>> coordinates = Arrays.asList(Arrays.asList(Arrays.asList(0d,0d),Arrays.asList(5d,5d),Arrays.asList(5d,0d),Arrays.asList(0d,0d)));
        Geometry geometry = new Geometry("Polygon",coordinates);
        polygon.setGeometry(geometry);
        boolean expectedResult = true;
        boolean actualResult = polygonFinder.isInPolygon(polygon,position);
        assertEquals(expectedResult,actualResult);
    }
}