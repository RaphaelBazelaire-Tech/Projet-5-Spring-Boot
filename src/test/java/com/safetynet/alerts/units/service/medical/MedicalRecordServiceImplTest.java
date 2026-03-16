package com.safetynet.alerts.units.service.medical;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.medical.MedicalRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceImplTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordServiceImpl;

    @Test
    public void addMedicalRecordShouldAddMedicalRecord() {

        List<MedicalRecord> records = new ArrayList<>();
        when(dataRepository.getMedicalrecords()).thenReturn(records);

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        medicalRecordServiceImpl.addMedicalRecord(record);

        assertEquals(1, records.size());
        assertEquals("John", records.get(0).getFirstName());
    }

    @Test
    public void updateMedicalRecordShouldUpdateMedicalRecord() {

        MedicalRecord existingRecord = new MedicalRecord(
                "John",
                "Boyd",
                "01/01/1990",
                List.of("oldMed"),
                List.of("oldAllergy")
        );

        List<MedicalRecord> records = new ArrayList<>();
        records.add(existingRecord);

        when(dataRepository.getMedicalrecords()).thenReturn(records);

        MedicalRecord updatedRecord = new MedicalRecord(
                "John",
                "Boyd",
                "02/02/2000",
                List.of("newMed"),
                List.of("newAllergy")
        );

        medicalRecordServiceImpl.updateMedicalRecord(updatedRecord);

        assertEquals("02/02/2000", existingRecord.getBirthdate());
        assertEquals("newMed", existingRecord.getMedications().get(0));
        assertEquals("newAllergy", existingRecord.getAllergies().get(0));
    }

    @Test
    public void deleteMedicalRecordShouldDeleteMedicalRecord() {

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of(),
                List.of()
        );

        List<MedicalRecord> records = new ArrayList<>();
        records.add(record);

        when(dataRepository.getMedicalrecords()).thenReturn(records);

        medicalRecordServiceImpl.deleteMedicalRecord("John", "Boyd");

        assertTrue(records.isEmpty());
    }

    @Test
    public void getMedicalRecordsShouldReturnListOfMedicalRecords() {

        List<MedicalRecord> records = List.of(
                new MedicalRecord("John", "Boyd", "03/06/1984", List.of(), List.of()),
                new MedicalRecord("Jacob", "Boyd", "05/05/1990", List.of(), List.of())
        );

        when(dataRepository.getMedicalrecords()).thenReturn(records);

        List<MedicalRecord> result = medicalRecordServiceImpl.getMedicalRecords();

        assertEquals(2, result.size());
    }

    @Test
    public void getMedicalRecordByNameShouldReturnMedicalRecord() {

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of(),
                List.of()
        );

        when(dataRepository.getMedicalrecords()).thenReturn(List.of(record));

        MedicalRecord result = medicalRecordServiceImpl.getMedicalRecordByName("John", "Boyd");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void getMedicalRecordNyNameShouldReturnNullIfNotFound() {

        when(dataRepository.getMedicalrecords()).thenReturn(new ArrayList<>());

        MedicalRecord result = medicalRecordServiceImpl.getMedicalRecordByName("John", "Boyd");

        assertNull(result);
    }
}
