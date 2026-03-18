package com.safetynet.alerts.controller.dto.person;

/**
 * Data Transfer Object (DTO) représentant une
 * vue simplifiée des informations d'une personne.
 *
 * <p>Ce DTO est utilisé lorsque seules les informations essentielles
 * de contact sont nécessaires, notamment dans les listes de personnes
 * couvertes par une station de pompiers.</p>
 */
public class PersonSummaryDTO {

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
     * Numéro de téléphone de la personne.
     */
    private String phone;

    /**
     * Constructeur par défaut.
     */
    public PersonSummaryDTO() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille.
     * @param address adresse de résidence.
     * @param phone numéro de téléphone.
     */
    public PersonSummaryDTO(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
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
     * Récupère le numéro de téléphone.
     *
     * @return numéro de téléphone.
     */
    public String getPhone() {
        return phone;
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
     * Définit le numéro de téléphone.
     *
     * @param phone numéro de téléphone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
