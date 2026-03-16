package com.safetynet.alerts.units.service.firestation;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.firestation.FirestationServiceImpl;
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
public class FirestationServiceImplTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private FirestationServiceImpl firestationServiceImpl;

    @Test
    public void addFirestationShouldAddFirestation() {

        List<Firestation> firestations = new ArrayList<>();
        when(dataRepository.getFirestations()).thenReturn(firestations);

        Firestation firestation = new Firestation("1509 Culver St", 3);

        firestationServiceImpl.addFirestation(firestation);

        assertEquals(1, firestations.size());
        assertEquals("1509 Culver St", firestations.get(0).getAddress());
        assertEquals(3, firestations.get(0).getStation());
    }

    @Test
    public void updateFirestationShouldUpdateStationNumber() {

        Firestation existingFirestation = new Firestation("1509 Culver St", 1);
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(existingFirestation);

        when(dataRepository.getFirestations()).thenReturn(firestations);

        Firestation updatedFirestation = new Firestation("1509 Culver St", 5);

        firestationServiceImpl.updateFirestation(updatedFirestation);

        assertEquals(5, existingFirestation.getStation());
    }

    @Test
    public void deleteFirestationShouldDeleteFirestation() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        when(dataRepository.getFirestations()).thenReturn(firestations);

        firestationServiceImpl.deleteFirestation("1509 Culver St");

        assertTrue(firestations.isEmpty());
    }

    @Test
    public void getFirestationsShouldReturnListOfFirestations() {

        List<Firestation> firestations = List.of(
                new Firestation("1509 Culver St", 1),
                new Firestation("29 15th St", 2)
        );

        when(dataRepository.getFirestations()).thenReturn(firestations);

        List<Firestation> result = firestationServiceImpl.getFirestations();

        assertEquals(2, result.size());
    }

    @Test
    public void getFirestationByAddressShouldReturnFirestation() {

        Firestation firestation = new Firestation("1509 Culver St", 1);

        when(dataRepository.getFirestations()).thenReturn(List.of(firestation));

        Firestation result = firestationServiceImpl.getFirestationByAddress("1509 Culver St");

        assertNotNull(result);
        assertEquals(1, result.getStation());
    }

    @Test
    public void getFirestationByAddressShouldReturnNullIfNotFound() {

        when(dataRepository.getFirestations()).thenReturn(new ArrayList<>());

        Firestation result = firestationServiceImpl.getFirestationByAddress("Unknown Address");

        assertNull(result);
    }
}
