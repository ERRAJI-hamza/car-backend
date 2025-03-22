package com.fleet.fleet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fleet.fleet.*;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="manager")
public class Manager {
    //To Do rename manager to compte
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String role;



    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ManagerFleet> managerFleets;

}
