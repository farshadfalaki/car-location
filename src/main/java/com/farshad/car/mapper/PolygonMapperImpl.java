package com.farshad.car.mapper;

import com.farshad.car.dto.PolygonWithCarsDto;
import com.farshad.car.model.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolygonMapperImpl implements PolygonMapper{
    public  PolygonWithCarsDto toDto(Polygon polygon, List<String> vins){
        return new PolygonWithCarsDto(polygon.getId(),polygon.getUpdatedAt(),polygon.getCreatedAt(),polygon.getName(),polygon.getCityId(),polygon.getLegacyId(),polygon.getType(),vins);
    }
}
