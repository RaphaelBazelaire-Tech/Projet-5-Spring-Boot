package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.medical.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST /medicalRecord - Ajout d'une point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());

        medicalRecordService.addMedicalRecord(medicalRecord);

        return ResponseEntity.ok("Point médical ajouté avec succès.");
    }

    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("PUT /medicalRecord - Mise à jour d'un point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());

        medicalRecordService.updateMedicalRecord(medicalRecord);

        return ResponseEntity.ok("Point médical mise à jour avec succès.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("DELETE /medicalRecord - Suppression d'un point médical pour {} {}", firstName, lastName);

        medicalRecordService.deleteMedicalRecord(firstName, lastName);

        return ResponseEntity.ok("Point médical supprimé avec succès.");
    }
}
