package com.farshad.car.scheduler;

import com.farshad.car.client.CarFetcherClient;
import com.farshad.car.dto.CarDto;
import com.farshad.car.dto.CarsInLocationResponseDto;
import com.farshad.car.dto.Position;
import com.farshad.car.mapper.CarMapper;
import com.farshad.car.model.Car;
import com.farshad.car.model.Polygon;
import com.farshad.car.service.CarService;
import com.farshad.car.service.PolygonService;
import com.farshad.car.util.PolygonFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class CarDataFetcherTest {
    @Mock
    CarService carService;
    @Mock
    CarFetcherClient carFetcherClient;
    @Mock
    CarMapper carMapper;
    @Mock
    PolygonFinder polygonFinder;
    @Mock
    PolygonService polygonService;
    @InjectMocks
    CarDataFetcher carDataFetcher;
    @Test
    public void carFetchFlowTest_withTwoPolygonsAndTwoCars_shouldUpsertTwoCars(){
        String polygonId1= "p1";
        String polygonId2= "p2";
        Polygon polygon1 = new Polygon();
        polygon1.setId(polygonId1);
        Polygon polygon2 = new Polygon();
        polygon2.setId(polygonId2);
        List<Polygon> polygonList =Arrays.asList(polygon1,polygon2);
        Position position1 = new Position(1.25d,1.5d);
        Position position2 = new Position(2.25d,2.5d);
        CarDto carDto1 = new CarDto(1l,"111","loc","11-111 1",position1,0d,"VW");
        Car car1 = new Car(1l,"111","11-111 1",position1,0d,"VW",polygonId1, Instant.now());
        CarDto carDto2 = new CarDto(2l,"222","loc","22-222 2",position2,0d,"PG");
        Car car2 = new Car(2l,"222","22-222 2",position1,0d,"VW",polygonId2, Instant.now());
        CarsInLocationResponseDto carsInLocationResponseDto = new CarsInLocationResponseDto(Arrays.asList(carDto1,carDto2));
        when(carFetcherClient.fetchCarsData()).thenReturn(Mono.just(carsInLocationResponseDto));
        when(polygonFinder.findPolygonFromLocation(polygonList,position1)).thenReturn(Optional.of(polygon1));
        when(polygonFinder.findPolygonFromLocation(polygonList,position2)).thenReturn(Optional.of(polygon2));
        when(carMapper.toModel(carDto1,polygonId1)).thenReturn(car1);
        when(carMapper.toModel(carDto2,polygonId2)).thenReturn(car2);
        when(carService.save(car1)).thenReturn(Mono.just(car1));
        when(carService.save(car2)).thenReturn(Mono.just(car2));
        Flux<Car> carFlux = carDataFetcher.carFetchFlow(polygonList);
        StepVerifier.create(carFlux).expectSubscription().expectNextCount(2).verifyComplete();
        verify(carService,times(2)).save(any());
        verify(carMapper,times(2)).toModel(any(),any());
        verify(polygonFinder,times(1)).findPolygonFromLocation(polygonList,position1);
        verify(polygonFinder,times(1)).findPolygonFromLocation(polygonList,position2);
        verify(carFetcherClient).fetchCarsData();
    }

}