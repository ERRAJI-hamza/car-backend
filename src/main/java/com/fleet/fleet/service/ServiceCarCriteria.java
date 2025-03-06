package com.fleet.fleet.service;

import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.CarCriteria;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.dto.CarCriteriaDto;
import com.fleet.fleet.mapper.FleetMapper;
import com.fleet.fleet.repo.RepoCar;
import com.fleet.fleet.repo.RepoCarCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceCarCriteria {

    private final RepoCarCriteria repoCarCriteria;

    private final RepoCar repoCar;

    private final FleetMapper fleetMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CarCriteriaDto postCarCriteria(CarCriteriaDto carCriteria,String vin) {
        Car car = repoCar.findById(vin).orElseThrow(() -> new RuntimeException("Pas de car trouvé"));

        CarCriteria XcarCriteria = CarCriteria.builder()
                                    .car(car)
                                    .idCarCriteria(carCriteria.getIdCarCriteria())
                                    .location(carCriteria.getLocation())
                                    .energieCharge(carCriteria.getEnergieCharge())
                                    .distance(carCriteria.getDistance())
                                    .date(LocalDate.now().format(formatter))
                                    .build();

        return fleetMapper.map(repoCarCriteria.save(XcarCriteria));
    }

}
