package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.dto.CarDto;
import com.fleet.fleet.dto.DriverDto;
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

    @GetMapping(value="/driver-to-car")
    public ResponseEntity<CarDto> postDriverToCar( @RequestParam("idDriver") Long idDriver,
                                                   @RequestParam("vin") String vin) {
        return new ResponseEntity<>(serviceCar.postDriverToCar(idDriver,vin), HttpStatus.CREATED);
    }

}
