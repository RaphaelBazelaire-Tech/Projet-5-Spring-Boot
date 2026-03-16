package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.firestation.FirestationService;
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
public class FirestationControllerTest {

    @Mock
    private FirestationService firestationService;

    @InjectMocks
    private FirestationController firestationController;

    @Test
    public void addFirestationShouldReturnSuccessMessage() {

        Firestation firestation = new Firestation();
        firestation.setAddress("1509 Culver St");
        firestation.setStation(1);

        ResponseEntity<String> result = firestationController.addFirestation(firestation);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Station de pompiers ajouté avec succès.", result.getBody());

        verify(firestationService, times(1)).addFirestation(firestation);

    }

    @Test
    public void updateFirestationShouldReturnSuccessMessage() {

        Firestation firestation = new Firestation();
        firestation.setAddress("1509 Culver St");
        firestation.setStation(2);

        ResponseEntity<String> result = firestationController.updateFirestation(firestation);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Adresse de la station mise à jour avec succès.", result.getBody());

        verify(firestationService, times(1)).updateFirestation(firestation);
    }

    @Test
    public void deleteFirestationShouldReturnSuccessMessage() {

        String address = "1509 Culver St";

        ResponseEntity<String> result = firestationController.deleteFirestation(address);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Suppression d'une adresse réalisé avec succès.", result.getBody());

        verify(firestationService, times(1)).deleteFirestation(address);
    }
}
