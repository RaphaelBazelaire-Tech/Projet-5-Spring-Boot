package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.firestation.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    private final FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @PostMapping
    public ResponseEntity<String> addFirestation(@RequestBody Firestation firestation) {
        logger.info("POST /firestation - Ajout d'une adresse {} pour la station {}", firestation.getAddress(), firestation.getStation());

        firestationService.addFirestation(firestation);

        return ResponseEntity.ok("Station de pompiers ajouté avec succès.");
    }

    @PutMapping
    public ResponseEntity<String> updateFirestation(@RequestBody Firestation firestation) {
        logger.info("PUT /firestation - Mise à jour de l'adresse {}", firestation.getAddress());

        firestationService.updateFirestation(firestation);

        return ResponseEntity.ok("Adresse de la station mise à jour avec succès.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFirestation(@RequestParam String address) {
        logger.info("DELETE /firestation - Suppression d'une adresse {}", address);

        firestationService.deleteFirestation(address);

        return ResponseEntity.ok("Suppression d'une adresse réalisé avec succès.");
    }
}

