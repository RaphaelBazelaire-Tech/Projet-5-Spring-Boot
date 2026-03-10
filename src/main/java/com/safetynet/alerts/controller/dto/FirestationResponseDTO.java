package com.safetynet.alerts.controller.dto;

import java.util.List;

public class FirestationResponseDTO {

    private List<PersonSummaryDTO> persons;
    private int adultCount;
    private int childCount;

    public FirestationResponseDTO() {
    }

    public FirestationResponseDTO(List<PersonSummaryDTO> persons, int adultCount, int childCount) {
        this.persons = persons;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

    public List<PersonSummaryDTO> getPersons() {
        return persons;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setPersons(List<PersonSummaryDTO> persons) {
        this.persons = persons;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
