package com.safetynet.alerts.controller.dto.fire;

import java.util.List;

public class FireAddressDTO {

    private int stationNumber;
    private List<FirePersonDTO> residents;

    public FireAddressDTO() {
    }

    public FireAddressDTO(int stationNumber, List<FirePersonDTO> residents) {
        this.stationNumber = stationNumber;
        this.residents = residents;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public List<FirePersonDTO> getResidents() {
        return residents;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void setResidents(List<FirePersonDTO> residents) {
        this.residents = residents;
    }
}
