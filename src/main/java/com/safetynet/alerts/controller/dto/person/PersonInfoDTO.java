package com.safetynet.alerts.controller.dto.person;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant les informations détaillées
 * d'une personne, incluant ses données personnelles et médicales.
 *
 * <p>Ce DTO est utilisé pour fournir une vue complète d'un individu,
 * notamment dans le cadre de recherches par nom ou prénom.</p>
 */
public class PersonInfoDTO {

    /**
     * Prénom de la personne.
     */
    private String firstName;

    /**
     * Nom de famille de la personne.
     */
    private String lastName;

    /**
     * Adresse de résidence de la personne.
     */
    private String address;

    /**
     * Âge de la personne.
     */
    private int age;

    /**
     * Adresse email de la personne.
     */
    private String email;

    /**
     * Liste des médicaments pris par la personne.
     */
    private List<String> medications;

    /**
     * Liste des allergies de la personne.
     */
    private List<String> allergies;

    /**
     * Constructeur par défaut.
     */
    public PersonInfoDTO() {
    }

    /**
     * Constructeur par initialisation complète.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @param address adresse de résidence.
     * @param age âge de la personne.
     * @param email adresse email.
     * @param medications liste des médicaments.
     * @param allergies liste des allergies.
     */
    public PersonInfoDTO(String firstName, String lastName, String address, int age, String email, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

    /**
     * Récupère le prénom de la personne.
     *
     * @return prénom de la personne.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Récupère le nom de famille de la personne.
     *
     * @return nom de famille de la personne.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Récupère l'adresse de résidence.
     *
     * @return adresse de résidence.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Récupère l'âge de la personne.
     *
     * @return âge de la personne.
     */
    public int getAge() {
        return age;
    }

    /**
     * Récupère l'adresse email.
     *
     * @return adresse email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Récupère la liste des médicaments.
     *
     * @return liste des médicaments.
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Récupère la liste des allergies.
     *
     * @return liste des allergies.
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Définit le prénom de la personne.
     *
     * @param firstName prénom de la personne.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Définit le nom de famille de la personne.
     *
     * @param lastName nom de famille de la personne.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Définit l'adresse de résidence.
     *
     * @param address adresse de résidence.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Définit l'âge de la personne.
     *
     * @param age âge de la personne.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Définit l'adresse email.
     *
     * @param email adresse email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définit la liste des médicaments.
     *
     * @param medications liste des médicaments.
     */
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    /**
     * Définit la liste des allergies.
     *
     * @param allergies liste des allergies.
     */
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
