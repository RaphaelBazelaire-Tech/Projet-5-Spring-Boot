package com.safetynet.alerts.units.controller;

import com.safetynet.alerts.controller.AlertController;
import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;
import com.safetynet.alerts.service.alert.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertControllerTest {

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    private FirestationResponseDTO firestationResponseDTO;

    @BeforeEach
    public void setUp() {
        firestationResponseDTO = new FirestationResponseDTO();
    }

    @Test
    public void getPersonCoveredByStationShouldReturnPersons() {

        int stationNumber = 1;

        when(alertService.getPersonCoveredByStation(stationNumber)).thenReturn(firestationResponseDTO);

        ResponseEntity<FirestationResponseDTO> result = alertController.getPersonCoveredByStation(stationNumber);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(firestationResponseDTO, result.getBody());

        verify(alertService, times(1)).getPersonCoveredByStation(stationNumber);
    }

    @Test
    public void getChildAddressShouldReturnChildren() {

        String address = "1509 Culver St";
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();

        when(alertService.getChildrenByAddress(address)).thenReturn(childAlertDTO);

        ResponseEntity<ChildAlertDTO> result = alertController.getChildAddress(address);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(childAlertDTO, result.getBody());

        verify(alertService).getChildrenByAddress(address);
    }

    @Test
    public void getPhoneAddressCoveredByStationShouldReturnPhones() {

        int stationNumber = 1;
        List<String> phones = List.of("123456789");

        when(alertService.getPhoneNumbersByStation(stationNumber))
                .thenReturn(phones);

        ResponseEntity<List<String>> result = alertController.getPhoneAddressCoveredByStation(stationNumber);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(phones, result.getBody());

        verify(alertService).getPhoneNumbersByStation(stationNumber);
    }

    @Test
    public void getFireByAddressShouldReturnFireInfo() {

        String address = "1509 Culver St";
        FireAddressDTO fireAddressDTO = new FireAddressDTO();

        when(alertService.getFireByAddress(address)).thenReturn(fireAddressDTO);

        ResponseEntity<FireAddressDTO> result = alertController.getFireByAddress(address);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(fireAddressDTO, result.getBody());

        verify(alertService).getFireByAddress(address);
    }

    @Test
    public void getFloodByStationShouldReturnFloodInfo() {

        List<Integer> stations =  List.of(1, 2, 3);
        Map<Integer, List<FloodDTO>> floodData = Map.of();

        when(alertService.getFloodByStations(stations)).thenReturn(floodData);

        ResponseEntity<Map<Integer, List<FloodDTO>>> result = alertController.getFloodByStation(stations);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(floodData, result.getBody());

        verify(alertService).getFloodByStations(stations);
    }

    @Test
    public void getPersonInfoByLastNameShouldReturnPersons() {

        String lastName = "Boyd";
        List<PersonInfoDTO> persons = List.of();

        when(alertService.getPersonInfoByLastName(lastName)).thenReturn(persons);

        ResponseEntity<List<PersonInfoDTO>> result = alertController.getPersonInfoByLastName(lastName);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(persons, result.getBody());

        verify(alertService).getPersonInfoByLastName(lastName);
    }

    @Test
    public void getCommunityEmailShouldReturnEmails() {

        String city = "Culver";
        List<String> emails = List.of("test@test.com");

        when(alertService.getCommunityEmails(city)).thenReturn(emails);

        ResponseEntity<List<String>> result = alertController.getCommunityEmail(city);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(emails, result.getBody());

        verify(alertService).getCommunityEmails(city);
    }
}
