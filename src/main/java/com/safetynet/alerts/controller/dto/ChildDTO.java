package com.safetynet.alerts.controller.dto;

import java.util.List;

public class ChildDTO {

    private String firstName;
    private String lastName;
    private int age;
    private List<String> householdMembers;

    public ChildDTO() {

    }

    public ChildDTO(String firstName, String lastName, int age, List<String> householdMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.householdMembers = householdMembers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHouseholdMembers() {
        return householdMembers;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHouseholdMembers(List<String> householdMembers) {
        this.householdMembers = householdMembers;
    }
}
