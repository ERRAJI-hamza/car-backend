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
public class DriverDto {

    private Long idDriver;

    private String name;
    private String prenom;

    private String address;

    private String rib;
}
