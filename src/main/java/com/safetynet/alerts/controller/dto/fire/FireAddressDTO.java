package com.safetynet.alerts.controller.dto.fire;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant les informations liées
 * à une adresse dans le cadre d'une alerte incendie.
 *
 * <p>Ce DTO contient le numéro de la station de pompiers
 * couvrant l'adresse ainsi que la liste des habitants
 * avec leurs informations.</p>
 */
public class FireAddressDTO {

    /**
     * Numéro de la station de pompiers couvrant l'adresse.
     */
    private int stationNumber;

    /**
     * Liste des habitants de l'adresse avec leurs informations.
     */
    private List<FirePersonDTO> residents;

    /**
     * Constructeur par défaut.
     */
    public FireAddressDTO() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param stationNumber numéro de la station de pompiers.
     * @param residents liste des habitants.
     */
    public FireAddressDTO(int stationNumber, List<FirePersonDTO> residents) {
        this.stationNumber = stationNumber;
        this.residents = residents;
    }

    /**
     * Récupère le numéro de la station de pompiers.
     *
     * @return numéro de la station de pompiers.
     */
    public int getStationNumber() {
        return stationNumber;
    }

    /**
     * Récupère la liste des habitants.
     *
     * @return liste des habitants.
     */
    public List<FirePersonDTO> getResidents() {
        return residents;
    }

    /**
     * Définit le numéro de la station de pompiers.
     *
     * @param stationNumber numéro de la station de pompiers.
     */
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    /**
     * Définit la liste des habitants.
     *
     * @param residents liste des habitants.
     */
    public void setResidents(List<FirePersonDTO> residents) {
        this.residents = residents;
    }
}
