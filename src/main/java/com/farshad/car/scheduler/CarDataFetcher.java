package com.farshad.car.scheduler;

import com.farshad.car.client.CarFetcherClient;
import com.farshad.car.dto.CarsInLocationResponseDto;
import com.farshad.car.mapper.CarMapper;
import com.farshad.car.model.Car;
import com.farshad.car.model.Polygon;
import com.farshad.car.service.CarService;
import com.farshad.car.service.PolygonService;
import com.farshad.car.util.PolygonFinder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class CarDataFetcher {
    private CarService          carService;
    private CarFetcherClient    carFetcherClient;
    private CarMapper           carMapper;
    private PolygonFinder       polygonFinder;
    private PolygonService      polygonService;

    @Scheduled(cron = "0/30 * * * * ?")
    @SchedulerLock(name = "fetchCarData", lockAtMostFor = "10s", lockAtLeastFor = "1s")
    public void fetchCarData(){
        log.info("Fetching Vehicles Data .......");
        List<Polygon> polygonList = polygonService.getAll().collectList().block();
        carFetchFlow(polygonList).subscribe();

    }
    public Flux<Car> carFetchFlow(List<Polygon> polygonList){
        return carFetcherClient.fetchCarsData()
                .flatMapIterable(CarsInLocationResponseDto::getCarDtos)
                .map(x-> carMapper.toModel(x,polygonFinder.findPolygonFromLocation(polygonList,x.getPosition()).orElse(new Polygon()).getId()))
                .flatMap(carService::save)
                .onErrorContinue((throwable, obj) -> log.warn("Error while persisting {} : {}",obj , throwable));
    }

}
