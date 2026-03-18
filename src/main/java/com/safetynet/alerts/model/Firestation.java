package com.safetynet.alerts.model;

/**
 * Entité représentant une station de pompiers.
 *
 * <p>Cette classe contient les informations essentielles d'une station,
 * à savoir son adresse et son numéro de station. Elle est utilisée par
 * les services et les DTO pour identifier les zones couvertes par chaque
 * station.</p>
 */
public class Firestation {

    /**
     * Adresse de la station.
     */
    private String address;

    /**
     * Numéro de la station de pompiers.
     */
    private int station;

    /**
     * Constructeur par défaut.
     */
    public Firestation() {
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param address adresse de la station.
     * @param station numéro de la station.
     */
    public Firestation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    /**
     * Récupère l'adresse de la station.
     *
     * @return adresse de la station.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Définit l'adresse de la station.
     *
     * @param address nouvelle adresse de la station.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Récupère le numéro de la station.
     *
     * @return numéro de la station.
     */
    public int getStation() {
        return station;
    }

    /**
     * Définit le nouveau numéro de la station.
     *
     * @param station nouveau numéro de la station.
     */
    public void setStation(int station) {
        this.station = station;
    }
}
