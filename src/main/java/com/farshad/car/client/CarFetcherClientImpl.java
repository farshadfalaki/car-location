package com.farshad.car.client;

import com.farshad.car.dto.CarDto;
import com.farshad.car.dto.CarsInLocationResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@Component
public class CarFetcherClientImpl implements  CarFetcherClient{
    private static String LOCATION = "Stuttgart";
    private static String URL = "http://localhost:3000";
    private static String PATH = "/vehicles/";
    private static String FULL_PATH = URL + PATH;
    private WebClient webClient = WebClient.builder().build();

    @Override
    public Mono<CarsInLocationResponseDto> fetchCarsData() {
        return fetchCarsData(LOCATION);
    }

    public Mono<CarsInLocationResponseDto> fetchCarsData(String locationName) {
        return webClient.get().uri(FULL_PATH + locationName)
                .exchange()
                .flatMap(response -> response.toEntityList(CarDto.class))
                .map(ResponseEntity::getBody)
                .map(body-> new CarsInLocationResponseDto(body))
                ;
    }
}
