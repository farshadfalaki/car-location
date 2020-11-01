package com.farshad.car.repository;

import com.farshad.car.model.Polygon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolygonRepository extends ReactiveMongoRepository<Polygon,String> {
}
