package com.fleet.fleet.domain;

import jakarta.persistence.*;
import com.fleet.fleet.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="managerfleet")
public class ManagerFleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String role;

    private String nameEts;
    private String refDossier;
    private String TVA;
    private String address;
    private String ville;
    private String pays;
    private String nameGestionner;
    private String email;
    private String tele;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "managerFleet",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Driver> drivers;
}
