package com.farshad.car.client;


import com.farshad.car.dto.CarsInLocationResponseDto;
import reactor.core.publisher.Mono;

public interface CarFetcherClient {
    Mono<CarsInLocationResponseDto> fetchCarsData();
}
