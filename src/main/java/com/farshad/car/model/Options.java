package com.farshad.car.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    private Boolean active;
    @JsonProperty("is_excluded")
    private Boolean excluded;
    private Double area;
}
