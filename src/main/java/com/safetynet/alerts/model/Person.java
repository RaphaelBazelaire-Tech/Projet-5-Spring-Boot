package com.safetynet.alerts.model;

/**
 * Entité représentant une personne dans le système SafetyNet.
 *
 * <p>Cette classe contient les informations personnelles nécessaires
 * pour la gestion des alertes, des dossiers médicaux et des
 * stations de pompiers.</p>
 *
 * <p>Elle est utilisée par les services, les controllers et les DTO
 * pour représenter chaque individu.</p>
 */
public class Person {

    /**
     * Prénom de la personne.
     */
    private String firstName;

    /**
     * Nom de famille de la personne.
     */
    private String lastName;

    /**
     * Adresse postale de la personne.
     */
    private String address;

    /**
     * Ville de résidence de la personne.
     */
    private String city;

    /**
     * Code postal de la personne.
     */
    private String zip;

    /**
     * Numéro de téléphone de la personne.
     */
    private String phone;

    /**
     * Adresse e-mail de la personne.
     */
    private String email;

    /**
     * Constructeur par défaut.
     */
    public Person() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @param address adresse postale.
     * @param city ville de résidence.
     * @param zip code postal.
     * @param phone numéro de téléphone.
     * @param email adresse e-mail.
     */
    public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
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
     * Récupère l'adresse postale de la personne.
     *
     * @return adresse postale de la personne.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Définit la nouvelle adresse postale.
     *
     * @param address nouvelle adresse postale.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Récupère la ville de résidence.
     *
     * @return ville de résidence.
     */
    public String getCity() {
        return city;
    }

    /**
     * Définit la nouvelle ville de résidence.
     *
     * @param city nouvelle ville de résidence.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Récupère le code postal.
     *
     * @return code postal.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Définit le nouveau code postal.
     *
     * @param zip nouveau code postal.
     */
    public void setZip(String zip) {
        this.zip = zip;
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
     * Définit le nouveau numéro de téléphone.
     *
     * @param phone nouveau numéro de téléphone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Définit la nouvelle adresse email.
     *
     * @param email nouvelle adresse email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
