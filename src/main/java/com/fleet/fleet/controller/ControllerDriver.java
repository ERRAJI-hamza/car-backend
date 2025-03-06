package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.DriverDto;
import com.fleet.fleet.service.ServiceDriver;
import com.fleet.fleet.service.ServiceManagerFleet;
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
public class ControllerDriver {
    private Logger logger = Logger.getLogger(ControllerManagerFleet.class.getName());
    private final ServiceDriver serviceDriver;

    @PostMapping(value="/ajouter-driver/{managerFleetId}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<DriverDto> postDriver(
                @RequestBody DriverDto driver,
                @PathVariable("managerFleetId") Long managerFleetId
    ) {
        logger.info("ajouter driver : " + driver);
        return new ResponseEntity<>(serviceDriver.postDriver(driver,managerFleetId), HttpStatus.CREATED);
    }

}
