package com.safetynet.alerts.controller.dto.flood;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant un foyer dans le cadre
 * d'une alerte de type flood (inondation ou situation d'urgence).
 *
 * <p>Ce DTO contient une adresse ainsi que la liste des habitants
 * associés à cette adresse avec leurs informations.</p>
 */
public class FloodDTO {

    /**
     * Adresse du foyer concerné.
     */
    private String address;

    /**
     * Liste des habitants résidant à cette adresse.
     */
    private List<FloodPersonDTO> residents;

    /**
     * Constructeur par défaut.
     */
    public FloodDTO() {
    }

    /**
     * Constructeur par initialisation complète.
     *
     * @param address adresse du foyer.
     * @param residents liste des habitants.
     */
    public FloodDTO(String address, List<FloodPersonDTO> residents) {
        this.address = address;
        this.residents = residents;
    }

    /**
     * Récupère l'adresse du foyer.
     *
     * @return adresse du foyer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Récupère la liste des habitants.
     *
     * @return liste des habitants.
     */
    public List<FloodPersonDTO> getResidents() {
        return residents;
    }

    /**
     * Définit l'adresse du foyer.
     *
     * @param address adresse du foyer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Définir la liste des habitants.
     *
     * @param residents liste des habitants.
     */
    public void setResidents(List<FloodPersonDTO> residents) {
        this.residents = residents;
    }
}
