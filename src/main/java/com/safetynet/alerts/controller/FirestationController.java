package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.alert.AlertService;
import com.safetynet.alerts.service.alert.AlertServiceImpl;
import com.safetynet.alerts.service.firestation.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    private final FirestationService firestationService;
    private final AlertService alertService;

    public FirestationController(FirestationService firestationService, AlertService alertService) {
        this.firestationService = firestationService;
        this.alertService = alertService;
    }

    @GetMapping
    public ResponseEntity<FirestationResponseDTO> getPersonCoveredByStation(@RequestParam int stationNumber) {

        return ResponseEntity.ok(alertService.getPersonCoveredByStation(stationNumber));
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

        return ResponseEntity.ok("Adresse de la station mise à jour succès.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFirestation(@RequestParam String address) {
        logger.info("DELETE /firestation - Suppression d'une adresse {}", address);

        firestationService.deleteFirestation(address);

        return ResponseEntity.ok("Suppression d'une adresse réalisé avec succès.");
    }
}

