package com.safetynet.alerts.controller.dto.firestation;

import com.safetynet.alerts.controller.dto.person.PersonSummaryDTO;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant la réponse
 * de l'endpoint lié aux stations de pompiers.
 *
 * <p>Ce DTO contient la liste des personnes couvertes par une station
 * ainsi que le nombre d'adultes et d'enfants.</p>
 */
public class FirestationResponseDTO {

    /**
     * Liste des personnes couvertes par la station.
     */
    private List<PersonSummaryDTO> persons;

    /**
     * Nombre d'adultes parmi les personnes couvertes.
     */
    private int adultCount;

    /**
     * Nombre d'enfants parmi les personnes couvertes.
     */
    private int childCount;

    /**
     * Constructeur par défaut.
     */
    public FirestationResponseDTO() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param persons liste des personnes couvertes.
     * @param adultCount nombre d'adultes.
     * @param childCount nombre d'enfants.
     */
    public FirestationResponseDTO(List<PersonSummaryDTO> persons, int adultCount, int childCount) {
        this.persons = persons;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

    /**
     * Récupère la liste des personnes couvertes.
     *
     * @return liste des personnes couvertes.
     */
    public List<PersonSummaryDTO> getPersons() {
        return persons;
    }

    /**
     * Récupère le nombre d'adultes.
     *
     * @return nombre d'adultes.
     */
    public int getAdultCount() {
        return adultCount;
    }

    /**
     * Récupère le nombre d'enfants.
     *
     * @return nombre d'enfants.
     */
    public int getChildCount() {
        return childCount;
    }

    /**
     * Définit la liste des personnes couvertes.
     *
     * @param persons liste des personnes couvertes.
     */
    public void setPersons(List<PersonSummaryDTO> persons) {
        this.persons = persons;
    }

    /**
     * Définit la liste du nombre d'adultes.
     *
     * @param adultCount nombre d'adultes.
     */
    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    /**
     * Définit la liste du nombre d'enfants.
     *
     * @param childCount nombre d'enfants.
     */
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
