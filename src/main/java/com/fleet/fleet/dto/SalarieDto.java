package com.fleet.fleet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalarieDto {
    private String marque;

    private String name;

    private String prenom;

    private String address;

    private String Rib;

    private String modele;

    private String immatriculation;

    private String dateImmatriculation;

    private String tarifDomicile;

    private String tarifTravail;

    private String Kilometrage;

    private String consomme;

    private String CO2;

}
