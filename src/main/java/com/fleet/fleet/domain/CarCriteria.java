package com.fleet.fleet.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CarCriteria")
public class CarCriteria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarCriteria;

    private String temperature;  // Température
    private String fuelLevel;    // Niveau de carburant
    private String brand;        // Marque
    private String batteryStatus; // État de la batterie
    private String location;
    private String distance;
    private String energieCharge;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vin")
    private Car car;
 }
