package com.fleet.fleet.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDriver;

    private String name;
    private String prenom;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_fleet_id")
    private ManagerFleet managerFleet;


    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

}
