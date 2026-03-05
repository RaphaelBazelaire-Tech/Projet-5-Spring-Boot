package com.safetynet.alerts.service.medical;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MedicalRecordServiceImpl implements MedicalRecordService {

    private static final Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

    private final DataRepository dataRepository;

    public MedicalRecordServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        logger.info("Ajout d'un point médical pour {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        dataRepository.getMedicalRecords().add(medicalRecord);
    }

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

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        logger.info("Suppression d'un point médical pour {} {}", firstName, lastName);

        dataRepository.getMedicalRecords()
                .removeIf(m -> m.getFirstName().equalsIgnoreCase(firstName)
                && m.getLastName().equalsIgnoreCase(lastName));
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return dataRepository.getMedicalRecords();
    }

    @Override
    public MedicalRecord getMedicalRecordByName(String firstName, String lastName) {
        return dataRepository.getMedicalRecords()
                .stream()
                .filter(m -> m.getFirstName().equalsIgnoreCase(firstName)
                && m.getFirstName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
