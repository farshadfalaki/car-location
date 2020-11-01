package com.farshad.car.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Polygon {
    @Id
    @JsonProperty("_id")
    private String id;
    private String updatedAt;
    private String createdAt;
    @JsonProperty("__v")
    private Double v;
    private String name;
    private String cityId;
    private String legacyId;
    private String type;
    private List<GeoFeature> geoFeatures = null;
    private Options options;
    private List<TimedOption> timedOptions = null;
    private Geometry geometry;
    private Double version;
    @JsonProperty("$computed")
    private Computed computed;
}
