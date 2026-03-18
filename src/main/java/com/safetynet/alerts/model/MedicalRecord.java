package com.safetynet.alerts.model;

import java.util.List;

/**
 * Entité représentant le dossier médical d'une personne.
 *
 * <p>Cette classe contient les informations personnelles nécessaires
 * pour la gestion des alertes, telles que le prénom, le nom, la date
 * de naissance, ainsi que les médicaments et allergies connus.</p>
 *
 * <p>La date de naissance est au format <code>MM/dd/yyyy</code>.</p>
 */
public class MedicalRecord {

    /**
     * Prénom de la personne.
     */
    private String firstName;

    /**
     * Nom de famille de la personne.
     */
    private String lastName;

    /**
     * Date de naissance de la personne au format MM/dd/yyyy.
     */
    private String birthdate;

    /**
     * Liste des médicaments pris par la personne.
     */
    private List<String> medications;

    /**
     * Liste des allergies connues de la personne.
     */
    private List<String> allergies;

    /**
     * Constructeur par défaut.
     */
    public MedicalRecord() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @param birthdate date de naissance au format MM/dd/yyyy.
     * @param medications liste des médicaments.
     * @param allergies liste des allergies.
     */
    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
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
     * Définit le nouveau prénom de la personne.
     *
     * @param firstName nouveau prénom de la personne.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Définit le nouveau nom de famille de la personne.
     *
     * @param lastName nouveau nom de famille de la personne.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Récupère la date de naissance.
     *
     * @return date de naissance au format MM/dd/yyyy.
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Définit la nouvelle date de naissance.
     *
     * @param birthdate nouvelle date de naissance au format MM/dd/yyyy.
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
     * Définit la nouvelle liste de médicaments.
     *
     * @param medications nouvelle liste de médicaments.
     */
    public void setMedications(List<String> medications) {
        this.medications = medications;
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
     * Définit la nouvelle liste d'allergies.
     *
     * @param allergies nouvelle liste d'allergies.
     */
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
