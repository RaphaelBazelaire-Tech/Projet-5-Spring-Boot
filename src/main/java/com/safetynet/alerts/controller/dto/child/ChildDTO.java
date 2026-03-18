package com.safetynet.alerts.controller.dto.child;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant un enfant
 * dans le cadre d'une alerte (child alert).
 *
 * <p>Ce DTO contient les informations essentielles sur un enfant
 * ainsi que les membres de son foyer vivant à la même adresse.</p>
 */
public class ChildDTO {

    /**
     * Prénom de l'enfant.
     */
    private String firstName;

    /**
     * Nom de famille de l'enfant.
     */
    private String lastName;

    /**
     * Âge de l'enfant.
     */
    private int age;

    /**
     * Liste des autres membres du foyer (prénom + nom).
     */
    private List<String> householdMembers;

    /**
     * Constructeur par défaut.
     */
    public ChildDTO() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param firstName prénom de l'enfant.
     * @param lastName nom de famille de l'enfant.
     * @param age âge de l'enfant.
     * @param householdMembers liste des membres du foyer.
     */
    public ChildDTO(String firstName, String lastName, int age, List<String> householdMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.householdMembers = householdMembers;
    }

    /**
     * Récupère le prénom de l'enfant.
     *
     * @return prénom de l'enfant.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Récupère le nom de famille de l'enfant.
     *
     * @return nom de famille de l'enfant.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Récupère l'âge de l'enfant.
     *
     * @return âge de l'enfant.
     */
    public int getAge() {
        return age;
    }

    /**
     * Récupère la liste des membres du foyer.
     *
     * @return liste des membres du foyer.
     */
    public List<String> getHouseholdMembers() {
        return householdMembers;
    }

    /**
     * Définit le prénom de l'enfant.
     *
     * @param firstName prénom de l'enfant.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Définit le nom de famille de l'enfant.
     *
     * @param lastName nom de famille de l'enfant.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Définit l'âge de l'enfant.
     *
     * @param age âge de l'enfant.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Définit la liste des membres du foyer.
     *
     * @param householdMembers liste des membres du foyer.
     */
    public void setHouseholdMembers(List<String> householdMembers) {
        this.householdMembers = householdMembers;
    }
}
