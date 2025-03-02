package com.fleet.fleet.repo;

import com.fleet.fleet.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoDriver extends JpaRepository<Driver,Long> {
    @Query("SELECT d FROM Driver d WHERE d.managerFleet.id = :managerFleetId")
    List<Driver> findByManagerFleetId(@Param("managerFleetId") Long managerFleetId);
}
