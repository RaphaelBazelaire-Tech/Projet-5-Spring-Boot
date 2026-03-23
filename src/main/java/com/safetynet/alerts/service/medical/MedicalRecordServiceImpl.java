package com.safetynet.alerts.service.medical;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service {@link MedicalRecordService}.
 *
 * <p>Ce service gère les opérations liées aux dossiers médicaux
 * dans l'application SafetyNet Alerts.</p>
 *
 * <p>Les fonctionnalités principales incluent :</p>
 * <ul>
 *     <li>l'ajout d'un nouveau dossier médical</li>
 *     <li>la mise à jour d'un dossier médical existant</li>
 *     <li>la suppression d'un dossier médical</li>
 *     <li>la récupération de l'ensemble des dossiers médicaux</li>
 *     <li>la recherche d'un dossier médical à partir du prénom et du nom</li>
 * </ul>
 *
 * <p>Les données sont récupérées et manipulées via le {@link DataRepository}
 * qui contient les informations chargées par l'application.</p>
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private static final Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

    private final DataRepository dataRepository;

    /**
     * Constructeur du service des dossiers médicaux.
     *
     * @param dataRepository dépôt de données contenant les dossiers médicaux.
     */
    public MedicalRecordServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * Ajoute un nouveau dossier médical pour une personne.
     *
     * @param medicalRecord objet {@link MedicalRecord} contenant les informations médicales de la personne.
     */
    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        logger.info("Ajout d'un point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        dataRepository.getMedicalRecords().add(medicalRecord);
    }

    /**
     * Met à jour un dossier médical existant.
     *
     * <p>La mise à jour se base sur le prénom et le nom de la personne.</p>
     *
     * @param medicalRecord objet {@link MedicalRecord} contenant les nouvelles informations médicales.
     */
    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        logger.info("Mise à jour d'un point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());

        Optional<MedicalRecord> existing = dataRepository.getMedicalRecords()
                .stream()
                .filter(m -> m.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
                        && m.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))
                .findFirst();

        existing.ifPresent(m -> {
            m.setBirthdate(medicalRecord.getBirthdate());
            m.setMedications(medicalRecord.getMedications());
            m.setAllergies(medicalRecord.getAllergies());
        });
    }

    /**
     * Supprime le dossier médical d'une personne.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     */
    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        logger.info("Suppression d'un point médical pour {} {}", firstName, lastName);

        dataRepository.getMedicalRecords()
                .removeIf(m -> m.getFirstName().equalsIgnoreCase(firstName)
                && m.getLastName().equalsIgnoreCase(lastName));
    }

    /**
     * Récupère la liste de tous les dossiers médicaux.
     *
     * @return une liste de {@link MedicalRecord}.
     */
    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return dataRepository.getMedicalRecords();
    }

    /**
     * Recherche un dossier médical à partir du prénom et du nom d'une personne.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return le {@link MedicalRecord} correspondant ou {@code null} si aucun dossier n'est trouvé.
     */
    @Override
    public MedicalRecord getMedicalRecordByName(String firstName, String lastName) {
        return dataRepository.getMedicalRecords()
                .stream()
                .filter(m -> m.getFirstName().equalsIgnoreCase(firstName)
                && m.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
