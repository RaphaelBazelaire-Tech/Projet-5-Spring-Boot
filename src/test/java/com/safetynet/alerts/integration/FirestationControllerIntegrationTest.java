package com.safetynet.alerts.integration;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.firestation.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FirestationService firestationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddFirestation() throws Exception {

        Firestation firestation = new Firestation("1509 Culver St", 3);

        doNothing().when(firestationService).addFirestation(firestation);

        mockMvc.perform(post("/firestation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFirestation() throws Exception {

        Firestation firestation = new Firestation("1509 Culver St", 5);

        doNothing().when(firestationService).updateFirestation(firestation);

        mockMvc.perform(put("/firestation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {

        doNothing().when(firestationService).deleteFirestation("1509 Culver St");

        mockMvc.perform(delete("/firestation")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }
}
