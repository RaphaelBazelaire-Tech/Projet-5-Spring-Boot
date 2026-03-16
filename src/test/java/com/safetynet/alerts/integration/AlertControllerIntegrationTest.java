package com.safetynet.alerts.integration;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;
import com.safetynet.alerts.service.alert.AlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlertService alertService;

    @Test
    public void testFirestationEndpoint() throws Exception {

        FirestationResponseDTO result = new FirestationResponseDTO(List.of(), 2, 1);

        when(alertService.getPersonCoveredByStation(1)).thenReturn(result);

        mockMvc.perform(get("/firestation")
                .param("stationNumber", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testChildAlertEndpoint() throws Exception {

        ChildAlertDTO result = new ChildAlertDTO(List.of());

        when(alertService.getChildrenByAddress("1509 Culver St")).thenReturn(result);

        mockMvc.perform(get("/childAlert")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFireEndpoint() throws Exception {

        FireAddressDTO result = new FireAddressDTO(1, List.of());

        when(alertService.getFireByAddress("1509 Culver St")).thenReturn(result);

        mockMvc.perform(get("/fire")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPersonInfoEndpoint() throws Exception {

        List<PersonInfoDTO> result = List.of();

        when(alertService.getPersonInfoByLastName("Boyd")).thenReturn(result);

        mockMvc.perform(get("/personInfo")
                .param("lastName", "Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCommunityEmailEndpoint() throws Exception {

        when(alertService.getCommunityEmails("Culver")).thenReturn(List.of("test@email.com"));

        mockMvc.perform(get("/communityEmail")
                .param("city", "Culver"))
                .andExpect(status().isOk());
    }
}
