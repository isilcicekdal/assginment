package com.smartict.assginment.dto;

import com.smartict.assginment.model.Route;
import com.smartict.assginment.model.Station;

import java.util.Set;

public class VehicleDto {

    private String plate;

    private Set<Station> stationList;
    private Route route;

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "plate='" + plate + '\'' +
                ", stationList=" + stationList +
                ", route=" + route +
                '}';
    }
}
