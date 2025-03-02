package com.fleet.fleet.repo;


import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoManagerFleet extends JpaRepository<ManagerFleet,Long> {
    List<ManagerFleet> findByManagerId(Long managerId);

    ManagerFleet findBynameEts(String username);
}
