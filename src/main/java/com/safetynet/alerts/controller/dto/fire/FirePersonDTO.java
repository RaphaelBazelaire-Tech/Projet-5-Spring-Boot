package com.safetynet.alerts.controller.dto.fire;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant un habitant dans
 * le cadre d'une alerte incendie.
 *
 * <p>Ce DTO contient les informations personnelles et médicales
 * d'une personne résidant à une adresse concernée par un incident.</p>
 */
public class FirePersonDTO {

    /**
     * Nom complet de la personne (prénom + nom).
     */
    private String name;

    /**
     * Numéro de téléphone de la personne.
     */
    private String phone;

    /**
     * Âge de la personne.
     */
    private int age;

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
    public FirePersonDTO() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param name nom complet de la personne.
     * @param phone numéro de téléphone.
     * @param age âge de la personne.
     * @param medications liste des médicaments.
     * @param allergies liste des allergies.
     */
    public FirePersonDTO(String name, String phone, int age, List<String> medications, List<String> allergies) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    /**
     * Récupère le nom complet de la personne.
     *
     * @return nom complet de la personne.
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère le numéro de téléphone.
     *
     * @return numéro de téléphone.
     */
    public String getPhone() {
        return phone;
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
     * Définit le nom complet de la personne.
     *
     * @param name nom complet de la personne.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le numéro de téléphone.
     *
     * @param phone numéro de téléphone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
