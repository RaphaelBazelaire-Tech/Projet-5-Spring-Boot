package com.safetynet.alerts.service.firestation;

import com.safetynet.alerts.model.Firestation;

import java.util.List;

/**
 * Interface définissant les opérations CRUD pour la gestion des stations de pompiers.
 *
 * <p>Fournit les méthodes pour ajouter, mettre à jour, supprimer
 * et récupérer des entités {@link Firestation}.</p>
 */
public interface FirestationService {

    /**
     * Ajoute une nouvelle station de pompiers.
     *
     * @param firestation station à ajouter.
     */
    void addFirestation(Firestation firestation);

    /**
     * Met à jour les informations d'une station existante.
     *
     * @param firestation station avec les nouvelles informations.
     */
    void updateFirestation(Firestation firestation);

    /**
     * Supprime une station identifiée par son adresse.
     *
     * @param address adresse de la station à supprimer.
     */
    void deleteFirestation(String address);

    /**
     * Récupère toutes les stations de pompiers.
     *
     * @return liste des {@link Firestation}.
     */
    List<Firestation> getFirestations();

    /**
     * Récupère une station par son adresse.
     *
     * @param address adresse de la station.
     * @return {@link Firestation} correspondant, ou null si non trouvé.
     */
    Firestation getFirestationByAddress(String address);
}
