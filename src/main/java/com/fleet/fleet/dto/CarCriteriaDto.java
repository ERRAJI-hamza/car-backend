package com.fleet.fleet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCriteriaDto {

    private Long idCarCriteria;

    private String location;
    private String distance;
    private String energieCharge;

    private String date;
}
