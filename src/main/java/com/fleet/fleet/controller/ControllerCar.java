package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.dto.AllFleetDto;
import com.fleet.fleet.dto.CarDto;
import com.fleet.fleet.dto.DriverDto;
import com.fleet.fleet.dto.SalarieDto;
import com.fleet.fleet.service.ServiceCar;
import com.fleet.fleet.service.ServiceDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ControllerCar{

    private Logger logger = Logger.getLogger(ControllerManagerFleet.class.getName());
    private final ServiceCar serviceCar;

    @PostMapping(value="/ajouter-car", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> postCar(@RequestBody CarDto car) {
        logger.info("ajouter car : " + car);
        return new ResponseEntity<>(serviceCar.postCar(car), HttpStatus.CREATED);
    }

    @PostMapping(value="/driver-to-car")
    public ResponseEntity<AllFleetDto> postDriverToCar(
            @RequestBody DriverDto driver,
            @RequestParam("idManagerFleet") Long idManagerFleet,
            @RequestParam("vin") String vin) {
        return new ResponseEntity<>(serviceCar.postDriverToCar(driver,idManagerFleet,vin), HttpStatus.CREATED);
    }

    @GetMapping(path="/get-salarie/{vin}")
    public ResponseEntity<SalarieDto> getSalarie(@PathVariable("vin") String vin) {
        logger.info("get managers fleet: " + vin);
        return new ResponseEntity<>(serviceCar.getSalarie(vin), HttpStatus.OK);
    }

}
