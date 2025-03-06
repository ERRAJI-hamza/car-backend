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
@Table(name = "car")
public class Car {
    @Id
    private String vin;

    private String marque;

    private String driverName;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "idDriver")
    private Driver driver;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CarCriteria> carCriteria;
}
