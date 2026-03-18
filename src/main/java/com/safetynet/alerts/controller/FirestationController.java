package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.firestation.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST permettant de gérer les associations entre
 * les adresses et les stations de pompiers dans l'application SafetyNet Alerts.
 *
 * <p>Ce contrôleur expose des endpoints permettant :</p>
 * <ul>
 *     <li>d'ajouter une nouvelle association adresse / station</li>
 *     <li>de mettre à jour la station associée à une adresse</li>
 *     <li>de supprimer une association existante</li>
 * </ul>
 *
 * <p>Les opérations sont déléguées au service {@link FirestationService}
 * qui contient la logique métier associée.</p>
 *
 * <p>Endpoint de base : <b>/firestation</b></p>
 */
@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    private final FirestationService firestationService;

    /**
     * Constructeur du contrôleur des stations de pompiers.
     *
     * @param firestationService le service responsable
     * de la gestion des stations de pompiers.
     */
    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    /**
     * Ajoute une nouvelle association entre une adresse et une station de pompiers.
     *
     * <p>Endpoint : <b>POST /firestation</b></p>
     *
     * @param firestation objet {@link Firestation} contenant l'adresse et le numéro de la station de pompiers.
     * @return une {@link ResponseEntity} contenant un message confirmant l'ajout de l'association.
     */
    @PostMapping
    public ResponseEntity<String> addFirestation(@RequestBody Firestation firestation) {
        logger.info("POST /firestation - Ajout d'une adresse {} pour la station {}", firestation.getAddress(), firestation.getStation());

        firestationService.addFirestation(firestation);

        return ResponseEntity.ok("Station de pompiers ajouté avec succès.");
    }

    /**
     * Met à jour la station de pompiers associée à une adresse existante.
     *
     * <p>Endpoint : <b>PUT /firestation</b></p>
     *
     * @param firestation objet {@link Firestation} contenant les nouvelles informations de la station.
     * @return une {@link ResponseEntity} contenant un message confirmant la mise à jour de l'association.
     */
    @PutMapping
    public ResponseEntity<String> updateFirestation(@RequestBody Firestation firestation) {
        logger.info("PUT /firestation - Mise à jour de l'adresse {}", firestation.getAddress());

        firestationService.updateFirestation(firestation);

        return ResponseEntity.ok("Adresse de la station mise à jour avec succès.");
    }

    /**
     * Supprime l'association entre une adresse et une station de pompiers.
     *
     * <p>Endpoint : <b>DELETE /firestation</b></p>
     *
     * @param address adresse dont l'association avec la station doit être supprimée.
     * @return une {@link ResponseEntity} contenant un message confirmant la suppression de l'association.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteFirestation(@RequestParam String address) {
        logger.info("DELETE /firestation - Suppression d'une adresse {}", address);

        firestationService.deleteFirestation(address);

        return ResponseEntity.ok("Suppression d'une adresse réalisé avec succès.");
    }
}

