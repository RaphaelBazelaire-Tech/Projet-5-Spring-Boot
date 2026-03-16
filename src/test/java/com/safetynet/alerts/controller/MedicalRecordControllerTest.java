package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.medical.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Test
    public void addMedicalRecordShouldReturnSuccessMessage() {

        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Boyd");

        ResponseEntity<String> result = medicalRecordController.addMedicalRecord(record);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Point médical ajouté avec succès.", result.getBody());

        verify(medicalRecordService, times(1)).addMedicalRecord(record);
    }

    @Test
    public void updateMedicalRecordShouldReturnSuccessMessage() {

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");

        ResponseEntity<String> result = medicalRecordController.updateMedicalRecord(medicalRecord);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Point médical mise à jour avec succès.", result.getBody());

        verify(medicalRecordService, times(1)).updateMedicalRecord(medicalRecord);
    }

    @Test
    public void deleteMedicalRecordShouldReturnSuccessMessage() {

        String firstName = "John";
        String lastName = "Boyd";

        ResponseEntity<String> result = medicalRecordController.deleteMedicalRecord(firstName, lastName);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Point médical supprimé avec succès.", result.getBody());

        verify(medicalRecordService, times(1)).deleteMedicalRecord(firstName, lastName);
    }
}
