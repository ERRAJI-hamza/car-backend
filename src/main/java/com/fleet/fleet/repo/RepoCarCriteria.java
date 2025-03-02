package com.fleet.fleet.repo;


import com.fleet.fleet.domain.CarCriteria;
import com.fleet.fleet.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCarCriteria extends JpaRepository<CarCriteria,Long> {
    @Query("SELECT c FROM CarCriteria c WHERE c.car.vin = :vin")
    List<CarCriteria> findByVin(@Param("vin") String vin);
}
