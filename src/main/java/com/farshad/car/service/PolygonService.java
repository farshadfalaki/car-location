package com.farshad.car.service;

import com.farshad.car.model.Polygon;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface PolygonService {
     Flux<Polygon> getAll();
}
