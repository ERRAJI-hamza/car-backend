package com.fleet.fleet.controller;


import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.userDto;
import com.fleet.fleet.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ControllerManager {
    private Logger logger = Logger.getLogger(ControllerManager.class.getName());
    private final ServiceManager serviceManager;

    @PostMapping(value="/ajouter-manager", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manager> postManager(@RequestBody Manager manager) {
        logger.info("ajouter manager: " + manager);
        return new ResponseEntity<>(serviceManager.postManager(manager), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginManager(@RequestBody userDto userDto) {
        logger.info("login: " + userDto);
        return new ResponseEntity<>(serviceManager.loginManager(userDto), HttpStatus.CREATED);
    }


}
