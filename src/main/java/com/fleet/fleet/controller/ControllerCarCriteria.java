package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.CarCriteria;
import com.fleet.fleet.dto.CarCriteriaDto;
import com.fleet.fleet.dto.CarDto;
import com.fleet.fleet.service.ServiceCar;
import com.fleet.fleet.service.ServiceCarCriteria;
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
public class ControllerCarCriteria {
    private Logger logger = Logger.getLogger(ControllerManagerFleet.class.getName());
    private final ServiceCarCriteria serviceCarCriteria;

    @PostMapping(value="/ajouter-car-criteria/{vin}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarCriteriaDto> postCarCriteria(
            @RequestBody CarCriteriaDto CarCriteria,
            @PathVariable("vin") String vin
    ) {
        logger.info("ajouter car : " + CarCriteria);
        return new ResponseEntity<>(serviceCarCriteria.postCarCriteria(CarCriteria,vin), HttpStatus.CREATED);
    }


}
