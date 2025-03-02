package com.fleet.fleet.repo;

import com.fleet.fleet.domain.Car;
import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.dto.CarDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCar extends JpaRepository<Car,String> {
    Car findByDriver_IdDriver(Long id);
}
