package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.medical.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST permettant de gérer les dossiers médicaux
 * des personnes dans l'application SafetyNet Alerts.
 *
 * <p>Ce contrôleur expose des endpoints permettant :</p>
 * <ul>
 *     <li>d'ajouter un nouveau dossier médical</li>
 *     <li>de mettre à jour un dossier médical existant</li>
 *     <li>de supprimer un dossier médical</li>
 * </ul>
 *
 * <p>Les opérations sont déléguées au service {@link MedicalRecordService}
 * qui contient la logique métier associée.</p>
 *
 * <p>Endpoint de base : <b>/medicalRecord</b></p>
 */
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    private final MedicalRecordService medicalRecordService;

    /**
     * Constructeur du contrôleur des dossiers médicaux.
     *
     * @param medicalRecordService le service responsable de la gestion des dossiers médicaux.
     */
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    /**
     * Ajoute un nouveau dossier médical pour une personne.
     *
     * <p>Endpoint : <b>POST /medicalRecord</b></p>
     *
     * @param medicalRecord objet {@link MedicalRecord} contenant les informations médicales de la personne.
     * @return une {@link ResponseEntity} contenant un message confirmant l'ajout du dossier médical.
     */
    @PostMapping
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST /medicalRecord - Ajout d'une point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());

        medicalRecordService.addMedicalRecord(medicalRecord);

        return ResponseEntity.ok("Point médical ajouté avec succès.");
    }

    /**
     * Met à jour le dossier médical d'une personne existante.
     *
     * <p>Endpoint : <b>PUT /medicalRecord</b></p>
     *
     * @param medicalRecord objet {@link MedicalRecord} contenant les nouvelles informations médicales.
     * @return une {@link ResponseEntity} contenant un message confirmant la mise à jour du dossier médical.
     */
    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("PUT /medicalRecord - Mise à jour d'un point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());

        medicalRecordService.updateMedicalRecord(medicalRecord);

        return ResponseEntity.ok("Point médical mise à jour avec succès.");
    }

    /**
     * Supprime le dossier médical d'une personne.
     *
     * <p>Endpoint : <b>DELETE /medicalRecord</b></p>
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return une {@link ResponseEntity} contenant un message confirmant la suppression du dossier médical.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("DELETE /medicalRecord - Suppression d'un point médical pour {} {}", firstName, lastName);

        medicalRecordService.deleteMedicalRecord(firstName, lastName);

        return ResponseEntity.ok("Point médical supprimé avec succès.");
    }
}
