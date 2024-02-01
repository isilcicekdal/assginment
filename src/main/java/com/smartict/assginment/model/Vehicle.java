package com.smartict.assginment.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "Must not be blank")
    // VehicleServiceImpl içinde de kontrol edilebilir.
    private String plate;

    @OneToMany(mappedBy = "vehicle")
    private Set<Station> stationList = new HashSet<>();


    public Vehicle() {
    }

    public Vehicle addStation(Station station) {
        this.stationList.add(station);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Set<Station> getStationList() {
        return stationList;
    }

    public void setStationList(Set<Station> stationList) {
        this.stationList = stationList;
    }
}
