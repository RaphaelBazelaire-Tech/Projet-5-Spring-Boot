package com.safetynet.alerts.controller.dto.flood;

import java.util.List;

public class FloodDTO {

    private String address;
    private List<FloodPersonDTO> residents;

    public FloodDTO() {
    }

    public FloodDTO(String address, List<FloodPersonDTO> residents) {
        this.address = address;
        this.residents = residents;
    }

    public String getAddress() {
        return address;
    }

    public List<FloodPersonDTO> getResidents() {
        return residents;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setResidents(List<FloodPersonDTO> residents) {
        this.residents = residents;
    }
}
