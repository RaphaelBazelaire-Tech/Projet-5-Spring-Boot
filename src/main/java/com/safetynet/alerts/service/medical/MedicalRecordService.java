package com.safetynet.alerts.service.medical;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    void addMedicalRecord(MedicalRecord medicalRecord);

    void updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(String firstName, String lastName);

    List<MedicalRecord> getMedicalRecords();

    MedicalRecord getMedicalRecordByName(String firstName, String lastName);
}
