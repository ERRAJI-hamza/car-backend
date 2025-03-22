package com.fleet.fleet.mapper;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.CarCriteria;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.CarCriteriaDto;
import com.fleet.fleet.dto.CarDto;
import com.fleet.fleet.dto.DriverDto;
import com.fleet.fleet.dto.ManagerFleetDto;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FleetMapper {

    @Mapping(target = "id",source = "id")
    @Mapping(target = "nameEts",source = "nameEts")
    @Mapping(target = "refDossier",source = "refDossier")
    @Mapping(target = "nameGestionner",source = "nameGestionner")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "tele",source = "tele")
    @Mapping(target = "address",source = "address")
    @Mapping(target = "Numdrivers",source = "drivers",qualifiedByName="mapdriver")
    ManagerFleetDto map(final ManagerFleet managerFleet);


    @Mapping(target = "idDriver",source = "idDriver")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "prenom",source = "prenom")
    @Mapping(target = "address",source = "address")
    @Mapping(target = "rib",source = "rib")
    DriverDto map(final Driver driver);

    @Mapping(target = "vin",source = "vin")
    @Mapping(target = "marque",source = "marque")
    @Mapping(target = "modele",source = "modele")
    @Mapping(target = "immatriculation",source = "immatriculation")
    @Mapping(target = "dateImmatriculation",source = "dateImmatriculation")
    @Mapping(target = "driverName",source = "driver",qualifiedByName="mapOnedriver")
    CarDto map(final Car car);

    @Mapping(target = "idCarCriteria",source = "idCarCriteria")
    @Mapping(target = "location",source = "location")
    @Mapping(target = "distance",source = "distance")
    @Mapping(target = "energieCharge",source = "energieCharge")
    @Mapping(target = "date",source = "date")
    CarCriteriaDto map(final CarCriteria carCriteria);


    @Named("mapdriver")
    default String mapdriver(final List<Driver> drivers){
        return CollectionUtils.isEmpty(drivers) ? "0" : String.valueOf(drivers.size());
    }

    @Named("mapOnedriver")
    default String mapOnedriver(final Driver driver){
       return  (driver != null) ? driver.getName() + "-" + driver.getPrenom() : null;
    }
}
