package com.fleet.fleet.service;

import com.fleet.fleet.domain.CarCriteria;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.*;
import com.fleet.fleet.helper.PasswordGenerator;
import com.fleet.fleet.mapper.FleetMapper;
import com.fleet.fleet.repo.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceManagerFleet {

    private final RepoManagerFleet repoManagerFleet;
    private final RepoManager repoManager;
    private final PasswordGenerator passwordGenerator;
    private final FleetMapper fleetMapper;
    private final RepoDriver repoDriver;
    private final RepoCar repoCar;
    private final RepoCarCriteria repoCarCriteria;

    public ManagerFleetDto postManagerFleet(ManagerFleetDto managerfleet,Long managerId) {
       Manager manager = repoManager.findById(managerId).orElseThrow(() -> new RuntimeException("Pas de manager trouvé"));

           ManagerFleet managerFleet = ManagerFleet.builder()
                   .manager(manager)
                   .nameEts(managerfleet.getNameEts())
                   .refDossier(managerfleet.getRefDossier())
                   .nameGestionner(managerfleet.getNameGestionner())
                   .email(managerfleet.getEmail())
                   .tele(managerfleet.getTele())
                   .role("USER")
                   .address(managerfleet.getAddress())
                   .password(PasswordGenerator.generatePassword(6,true))
                   .build();

           return fleetMapper.map(repoManagerFleet.save(managerFleet));
    }

    public List<ManagerFleetDto> getManagersFleet(Long managerId) {
        List<ManagerFleet> managerFleets = repoManagerFleet.findByManagerId(managerId);
        return managerFleets.stream()
                .map((managerFleet )-> fleetMapper.map(managerFleet))
                .collect(Collectors.toList());
    }

    public List<DriverDto> getAllDrivers(Long managerFleetId){
        List<Driver> drivers =  repoDriver.findByManagerFleetId(managerFleetId);
        return drivers.stream()
                .map((d)->fleetMapper.map(d))
                .collect(Collectors.toList());

    }

    public CarDto getCarDriver(Long idDriver){
        return fleetMapper.map(repoCar.findByDriver_IdDriver(idDriver)) ;

    }

    public List<CarDto> getAllCarDrivers(List<DriverDto> driverDtos){
        return driverDtos.stream()
                .map(driverDto -> getCarDriver(driverDto.getIdDriver()))
                .collect(Collectors.toList());
    }

    public List<CarCriteriaDto> getCarCriteriabyCar(String vin){
        List<CarCriteria> criterias = repoCarCriteria.findByVin(vin);
        return criterias.stream()
                .map((c)->fleetMapper.map(c))
                .collect(Collectors.toList());
    }

    public List<AllFleetDto> getAllFleet(List<CarDto> carDtos) {
        List<AllFleetDto> allFleetDtos = new ArrayList<>();

        for (CarDto carDto : carDtos) {
            if (carDto != null){
                List<CarCriteriaDto> carCriteriaList = getCarCriteriabyCar(carDto.getVin());
                allFleetDtos.add(
                        AllFleetDto.builder()
                                .driverName(carDto.getDriverName())
                                .vin(carDto.getVin())
                                .energieWork(getEnergieWork(carCriteriaList))
                                .energieHome(getEnergieHome(carCriteriaList))
                                .energiePublic(getEnergiePublic(carCriteriaList))
                                .energieTotal(getEnergieTotal(carCriteriaList))
                                .distance(getTotalDistance(carCriteriaList))
                                .build()
                );
            }
        }

        return allFleetDtos;
    }


    public List<AllFleetDto> getAllFleets(Long managerFleetId){
        List<DriverDto> driverDtos = getAllDrivers(managerFleetId);
        log.info("----{}",driverDtos);
        List<CarDto> carDtos = getAllCarDrivers(driverDtos);
        log.info("----{}",carDtos);
        List<AllFleetDto> allFleetDtos = getAllFleet(carDtos);
        log.info("----{}",allFleetDtos);
        return allFleetDtos;
    }

    private String getEnergieWork(List<CarCriteriaDto> carCriterias){
        int totalEnergy = carCriterias.stream()
                .filter(car -> "Work".equals(car.getLocation()))
                .map(car -> car.getEnergieCharge())
                .filter(energie -> energie.matches("\\d+Kwh"))
                .map(energie -> Integer.parseInt(energie.replace("Kwh", "")))
                .reduce(0, Integer::sum);

        return totalEnergy + "Kwh";
    }

    private String getEnergieHome(List<CarCriteriaDto> carCriterias){
        int totalEnergy = carCriterias.stream()
                .filter(car -> "Home".equals(car.getLocation()))
                .map(car -> car.getEnergieCharge())
                .filter(energie -> energie.matches("\\d+Kwh"))
                .map(energie -> Integer.parseInt(energie.replace("Kwh", "")))
                .reduce(0, Integer::sum);

        return totalEnergy + "Kwh";
    }

    private String getEnergiePublic(List<CarCriteriaDto> carCriterias){
        int totalEnergy = carCriterias.stream()
                .filter(car -> "Public".equals(car.getLocation()))
                .map(car -> car.getEnergieCharge())
                .filter(energie -> energie.matches("\\d+Kwh"))
                .map(energie -> Integer.parseInt(energie.replace("Kwh", "")))
                .reduce(0, Integer::sum);

        return totalEnergy + "Kwh";
    }

    private String getEnergieTotal(List<CarCriteriaDto> carCriterias){
        int totalEnergy = carCriterias.stream()
                .map(car -> car.getEnergieCharge())
                .filter(energie -> energie.matches("\\d+Kwh"))
                .map(energie -> Integer.parseInt(energie.replace("Kwh", "")))
                .reduce(0, Integer::sum);

        return totalEnergy + "Kwh";
    }

    private String getTotalDistance(List<CarCriteriaDto> carCriterias) {
        int totalDistance = carCriterias.stream()
                .map(CarCriteriaDto::getDistance) // Récupère la distance sous forme de String
                .filter(distance -> distance != null && distance.matches("\\d+")) // Vérifie que c'est bien un nombre
                .map(Integer::parseInt) // Convertit en entier
                .reduce(0, Integer::sum); // Fait la somme

        return totalDistance + "km"; // Retourne sous forme de String avec "km"
    }


}
