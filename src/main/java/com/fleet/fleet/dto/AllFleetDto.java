package com.fleet.fleet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllFleetDto {
    private String vin;
    private String driverName;
    private String energieWork;
    private String energieHome;
    private String energiePublic;
    private String energieTotal;
    private String distance;

}
