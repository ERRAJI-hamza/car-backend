package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.*;
import com.fleet.fleet.service.ServiceManager;
import com.fleet.fleet.service.ServiceManagerFleet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {})
public class ControllerManagerFleet {
    private Logger logger = Logger.getLogger(ControllerManagerFleet.class.getName());
    private final ServiceManagerFleet serviceManagerFleet;

    @PostMapping(value="/ajouter-manager-fleet/{managerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerFleetDto> postManagerFleet(
            @RequestBody ManagerFleetDto managerFleetDto,
            @PathVariable("managerId") Long managerId
    ) {
        logger.info("ajouter manager fleet: " + managerFleetDto);
        return new ResponseEntity<>(serviceManagerFleet.postManagerFleet(managerFleetDto,managerId), HttpStatus.CREATED);
    }

    @GetMapping(path="/get-managers-fleet/{managerId}")
    public ResponseEntity<List<ManagerFleetDto>> getManagersFleet(@PathVariable("managerId") Long managerId) {
        logger.info("get managers fleet: " + managerId);
        return new ResponseEntity<>(serviceManagerFleet.getManagersFleet(managerId), HttpStatus.OK);
    }

    @GetMapping(path="/get-drivers-by-fleet/{managerFleetId}")
    public ResponseEntity<List<DriverDto>> getAllDrivers(@PathVariable("managerFleetId") Long managerFleetId) {
        logger.info("get managers fleet: " + managerFleetId);
        return new ResponseEntity<>(serviceManagerFleet.getAllDrivers(managerFleetId), HttpStatus.OK);
    }

    @GetMapping(path="/get-car-by-driver/{driverId}")
    public ResponseEntity<CarDto> getCarDriver(@PathVariable("driverId") Long driverId) {
        logger.info("get managers fleet: " + driverId);
        return new ResponseEntity<>(serviceManagerFleet.getCarDriver(driverId), HttpStatus.OK);
    }

    @GetMapping(path="/get-carcriteria-by-car/{vin}")
    public ResponseEntity<List<CarCriteriaDto>> getCarCriteriabyCar(@PathVariable("vin") String vin) {
        logger.info("get managers fleet: " + vin);
        return new ResponseEntity<>(serviceManagerFleet.getCarCriteriabyCar(vin), HttpStatus.OK);
    }


    @GetMapping(path="/get-all/{managerFleetId}")
    public ResponseEntity<List<AllFleetDto>> getAllFleets(@PathVariable("managerFleetId") Long managerFleetId) {
        logger.info("get managers fleet: " + managerFleetId);
        return new ResponseEntity<>(serviceManagerFleet.getAllFleets(managerFleetId), HttpStatus.OK);
    }

}
