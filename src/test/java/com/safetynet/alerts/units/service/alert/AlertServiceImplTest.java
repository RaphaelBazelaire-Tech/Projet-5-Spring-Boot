package com.safetynet.alerts.units.service.alert;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.alert.AlertServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlertServiceImplTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private AlertServiceImpl alertServiceImpl;

    @Test
    public void getPersonCoveredByStationShouldReturnPersonsAndCounts() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));
        when(dataRepository.getPersons()).thenReturn(List.of(person));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        FirestationResponseDTO result = alertServiceImpl.getPersonCoveredByStation(1);

        assertNotNull(result);
        assertEquals(1, result.getPersons().size());
        assertEquals(1, result.getAdultCount());
        assertEquals(0, result.getChildCount());
    }

    @Test
    public void getChildrenByAddressShouldReturnChildrenList() {

        Person child =  new Person(
                "Tenley",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "tenley@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "Tenley",
                "Boyd",
                "02/18/2015",
                List.of(),
                List.of()
        );

        when(dataRepository.getPersons()).thenReturn(List.of(child));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        ChildAlertDTO result = alertServiceImpl.getChildrenByAddress("1509 Culver St");

        assertNotNull(result);
        assertEquals(1, result.getChildren().size());
        assertEquals("Tenley", result.getChildren().get(0).getFirstName());
    }

    @Test
    public void getPersonCoveredByStationShouldCountChildren() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        Person child = new Person(
                "Tenley",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "tenley@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "Tenley",
                "Boyd",
                "02/18/2015",
                List.of(),
                List.of()
        );

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));
        when(dataRepository.getPersons()).thenReturn(List.of(child));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        FirestationResponseDTO result = alertServiceImpl.getPersonCoveredByStation(1);

        assertNotNull(result);
        assertEquals(1, result.getPersons().size());
        assertEquals(0, result.getAdultCount());
        assertEquals(1, result.getChildCount());
    }

    @Test
    public void getPhoneNumbersByStationShouldReturnPhoneNumbers() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));
        when(dataRepository.getPersons()).thenReturn(List.of(person));

        List<String> phones = alertServiceImpl.getPhoneNumbersByStation(1);

        assertEquals(1, phones.size());
        assertEquals("841-874-6512", phones.get(0));
    }

    @Test
    public void getFireByAddressShouldReturnResidents() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));
        when(dataRepository.getPersons()).thenReturn(List.of(person));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        FireAddressDTO result = alertServiceImpl.getFireByAddress("1509 Culver St");

        assertNotNull(result);
        assertEquals(1, result.getStationNumber());
        assertEquals(1, result.getResidents().size());
    }

    @Test
    public void getFloodByStationsShouldReturnFloodInformation() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));
        when(dataRepository.getPersons()).thenReturn(List.of(person));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        Map<Integer, List<FloodDTO>> result = alertServiceImpl.getFloodByStations(List.of(1));

        assertNotNull(result);
        assertTrue(result.containsKey(1));
        assertEquals(1, result.get(1).size());
    }

    @Test
    public void getPersonInfoShouldReturnPersonInfo() {

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        when(dataRepository.getPersons()).thenReturn(List.of(person));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(record));

        List<PersonInfoDTO> result = alertServiceImpl.getPersonInfo("John", "Boyd");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Boyd", result.get(0).getLastName());
        assertEquals("john@email.com", result.get(0).getEmail());
    }

    @Test
    public void getPersonInfoByLastNameShouldReturnMultiplePersons() {

        Person firstPerson = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        Person secondPerson = new Person(
                "Jacob",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6513",
                "jacob@email.com"
        );

        MedicalRecord firstRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of(),
                List.of()
        );

        MedicalRecord secondRecord = new MedicalRecord(
                "Jacob",
                "Boyd",
                "03/06/1990",
                List.of(),
                List.of()
        );

        when(dataRepository.getPersons()).thenReturn(List.of(firstPerson, secondPerson));
        when(dataRepository.getMedicalRecords()).thenReturn(List.of(firstRecord, secondRecord));

        List<PersonInfoDTO> result = alertServiceImpl.getPersonInfoByLastName("Boyd");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void getCommunityEmailsShouldReturnEmails() {

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        when(dataRepository.getPersons()).thenReturn(List.of(person));

        List<String> emails = alertServiceImpl.getCommunityEmails("Culver");

        assertEquals(1, emails.size());
        assertEquals("john@email.com", emails.get(0));
    }
}
