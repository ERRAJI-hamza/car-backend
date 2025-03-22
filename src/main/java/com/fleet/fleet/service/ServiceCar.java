package com.fleet.fleet.service;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.*;
import com.fleet.fleet.mapper.FleetMapper;
import com.fleet.fleet.repo.RepoCar;
import com.fleet.fleet.repo.RepoDriver;
import com.fleet.fleet.repo.RepoManagerFleet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceCar {

    private final RepoCar repoCar;
    private final RepoDriver repoDriver;
    private final RepoManagerFleet repoManagerFleet;

    private final FleetMapper fleetMapper;

    private final ServiceManagerFleet serviceManagerFleet;

    public CarDto postCar(CarDto car) {
        Car carX = Car.builder()
                .vin(car.getVin())
                .marque(car.getMarque())
                .build();

        Car savedCar = repoCar.save(carX);
        return fleetMapper.map(savedCar);
    }

    public CarDto postDriverToCarX(DriverDto driver, Long idManagerFleet, String vin) {
        ManagerFleet managerFleet = repoManagerFleet.findById(idManagerFleet).orElseThrow(() -> new RuntimeException("Pas de manager trouvé"));

        Driver driverX = Driver.builder()
                .managerFleet(managerFleet)
                .name(driver.getName())
                .prenom(driver.getPrenom())
                .address(driver.getAddress())
                .build();

        Driver driverSave = repoDriver.save(driverX);

        Car car = repoCar.findById(vin).orElseThrow(() -> new RuntimeException("Voiture avec VIN " + vin + " non trouvée"));;
        car.setDriver(driverSave);
        return fleetMapper.map(repoCar.save(car));
    }

    public AllFleetDto postDriverToCar(DriverDto driver,Long idManagerFleet, String vin) {

        //Driver driverX = repoDriver.findById(idDriver).orElseThrow(() -> new RuntimeException("Pas de driver trouvé"));

        ManagerFleet managerFleet = repoManagerFleet.findById(idManagerFleet).orElseThrow(() -> new RuntimeException("Pas de manager trouvé"));

        Driver driverX = Driver.builder()
                .managerFleet(managerFleet)
                .name(driver.getName())
                .prenom(driver.getPrenom())
                .address(driver.getAddress())
                .build();

        repoDriver.save(driverX);

        Car car = repoCar.findById(vin).orElseThrow(() -> new RuntimeException("Voiture avec VIN " + vin + " non trouvée"));;
        car.setDriver(driverX);

        CarDto carDto= fleetMapper.map(repoCar.save(car));

        return AllFleetDto.builder()
                .driverName(carDto.getDriverName())
                .vin(carDto.getVin())
                .energieWork("OKwh")
                .energieHome("OKwh")
                .energiePublic("OKwh")
                .energieTotal("OKwh")
                .distance("Okm")
                .build();
    }

    public SalarieDto getSalarie(String vin) {
        Car car = repoCar.findById(vin).orElseThrow(() -> new RuntimeException("Voiture avec VIN " + vin + " non trouvée"));
        CarDto carDto= fleetMapper.map(car);
        DriverDto driver =fleetMapper.map(car.getDriver());
        List<CarCriteriaDto> carCriteriaDtos = serviceManagerFleet.getCarCriteriabyCar(carDto.getVin());


        return SalarieDto.builder()
                .name(driver.getName())
                .prenom(driver.getPrenom())
                .address(driver.getAddress())
                .Rib(driver.getRib())
                .modele(carDto.getModele())
                .marque(carDto.getMarque())
                .immatriculation(carDto.getImmatriculation())
                .dateImmatriculation(carDto.getDateImmatriculation())
                .tarifDomicile(serviceManagerFleet.getEnergieWork(carCriteriaDtos))
                .tarifTravail(serviceManagerFleet.getEnergieHome(carCriteriaDtos))
                .consomme(serviceManagerFleet.getEnergiePublic(carCriteriaDtos))
                .CO2(null)
                .Kilometrage(serviceManagerFleet.getTotalDistance(carCriteriaDtos))
                .build();
    }
}
