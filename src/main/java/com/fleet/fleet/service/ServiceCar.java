package com.fleet.fleet.service;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.CarDto;
import com.fleet.fleet.dto.DriverDto;
import com.fleet.fleet.mapper.FleetMapper;
import com.fleet.fleet.repo.RepoCar;
import com.fleet.fleet.repo.RepoDriver;
import com.fleet.fleet.repo.RepoManagerFleet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceCar {

    private final RepoCar repoCar;
    private final RepoDriver repoDriver;
    private final RepoManagerFleet repoManagerFleet;

    private final FleetMapper fleetMapper;

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

    public CarDto postDriverToCar(Long idDriver, String vin) {

        Driver driverX = repoDriver.findById(idDriver).orElseThrow(() -> new RuntimeException("Pas de driver trouvé"));

        Car car = repoCar.findById(vin).orElseThrow(() -> new RuntimeException("Voiture avec VIN " + vin + " non trouvée"));;
        car.setDriver(driverX);
        return fleetMapper.map(repoCar.save(car));
    }
}
