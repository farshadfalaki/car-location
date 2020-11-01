package com.farshad.car.model;

import com.farshad.car.dto.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@Document
public class Car {
    @Id
    private Long id;
    private String vin;
    private String numberPlate;
    private Position position;
    private Double fuel;
    private String model;
    private String polygonId;
    private Instant lastModified;

}
