package com.safetynet.alerts.service.medical;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

/**
 * Interface définissant les opérations CRUD pour la gestion des dossiers médicaux.
 *
 * <p>Fournit les méthodes pour ajouter, mettre à jour, supprimer
 * et récupérer des entités {@link MedicalRecord}.</p>
 */
public interface MedicalRecordService {

    /**
     * Ajoute un nouveau dossier médical.
     *
     * @param medicalRecord le dossier à ajouter.
     */
    void addMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Met à jour un dossier médical existant.
     *
     * @param medicalRecord le dossier avec les nouvelles informations.
     */
    void updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Supprime un dossier médical identifié par le prénom et le nom de famille.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     */
    void deleteMedicalRecord(String firstName, String lastName);

    /**
     * Récupère tous les dossiers médicaux présents dans le repository.
     *
     * @return liste des {@link MedicalRecord}.
     */
    List<MedicalRecord> getMedicalRecords();

    /**
     * Récupère un dossier médical par prénom et nom de famille.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return {@link MedicalRecord} correspondant, ou null si non trouvé.
     */
    MedicalRecord getMedicalRecordByName(String firstName, String lastName);
}
