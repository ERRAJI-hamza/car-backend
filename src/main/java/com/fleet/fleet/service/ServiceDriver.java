package com.fleet.fleet.service;


import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.DriverDto;
import com.fleet.fleet.helper.PasswordGenerator;
import com.fleet.fleet.mapper.FleetMapper;
import com.fleet.fleet.repo.RepoDriver;
import com.fleet.fleet.repo.RepoManagerFleet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceDriver {

    @Autowired
    private final RepoDriver repoDriver;
    @Autowired
    private final RepoManagerFleet repoManagerFleet;
    private final FleetMapper fleetMapper;

    public DriverDto postDriver(Driver driver) {
        ManagerFleet managerFleet = repoManagerFleet.findById(driver.getManagerFleet().getId()).orElseThrow(() -> new RuntimeException("Pas de manager trouvé"));

        Driver driverX = Driver.builder()
                .managerFleet(managerFleet)
                .name(driver.getName())
                .prenom(driver.getPrenom())
                .address(driver.getAddress())
                .build();

        return fleetMapper.map(repoDriver.save(driverX));
    }
}
