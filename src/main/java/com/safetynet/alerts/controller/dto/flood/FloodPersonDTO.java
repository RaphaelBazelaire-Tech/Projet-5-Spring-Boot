package com.safetynet.alerts.controller.dto.flood;

import java.util.List;

public class FloodPersonDTO {

    private String name;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public FloodPersonDTO() {
    }

    public FloodPersonDTO(String name, String phone, int age, List<String> medications, List<String> allergies) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
