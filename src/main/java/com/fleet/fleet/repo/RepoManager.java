package com.fleet.fleet.repo;



import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoManager extends JpaRepository<Manager,Long> {

    Manager findByUserName(String userName);
}
