package com.farshad.car.init;

import com.farshad.car.model.Polygon;
import com.farshad.car.repository.PolygonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class DataInit {
    private static final String POLYGON_DATA_URL = "https://gist.githubusercontent.com/codeofsumit/6540cdb245bd14c33b486b7981981b7b/raw/73ebda86c32923e203b2a8e61615da3e5f1695a2/polygons.json";

    private PolygonRepository polygonRepository;
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        log.info("Polygons Data init");
        polygonRepository.count().filter(count->count==0)
                .doOnNext(e->polygonRepository.insert(Flux.fromIterable(loadPolygonsDataFromURI())).subscribe())
                .subscribe();

    }
    public List<Polygon> loadPolygonsDataFromURI(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL(POLYGON_DATA_URL);
            Polygon[] polygons = objectMapper.readValue(url, Polygon[].class);
            return Arrays.asList(polygons);
        } catch (IOException e) {
            log.warn("Cannot read polygons data " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
