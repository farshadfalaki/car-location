package com.farshad.car.service;

import com.farshad.car.model.Polygon;
import com.farshad.car.repository.PolygonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
@Slf4j
public class PolygonServiceImpl implements PolygonService{
    private PolygonRepository polygonRepository;

    @Override
    @Cacheable("polygons")
    public Flux<Polygon> getAll() {
        log.info("Getting All Polygons");
        return polygonRepository.findAll().cache();
    }
}
