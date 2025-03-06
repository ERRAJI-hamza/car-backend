package com.fleet.fleet.dto;


import com.fleet.fleet.domain.Driver;
import com.fleet.fleet.domain.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerFleetDto {
    private Long id;
    private String nameEts;
    private String refDossier;
    private String address;
    private String nameGestionner;
    private String email;
    private String tele;
    private String Numdrivers;
}
