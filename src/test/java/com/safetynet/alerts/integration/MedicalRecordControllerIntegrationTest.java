package com.safetynet.alerts.integration;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.medical.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MedicalRecordService medicalRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddMedicalRecord() throws Exception {

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg"),
                List.of("nillacilan")
        );

        doNothing().when(medicalRecordService).addMedicalRecord(record);

        mockMvc.perform(post("/medicalRecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {

        MedicalRecord record = new MedicalRecord(
                "John",
                "Boyd",
                "04/04/1984",
                List.of("newMed:100mg"),
                List.of("pollen")
        );

        doNothing().when(medicalRecordService).updateMedicalRecord(record);

        mockMvc.perform(put("/medicalRecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {

        doNothing().when(medicalRecordService).deleteMedicalRecord("John", "Boyd");

        mockMvc.perform(delete("/medicalRecord")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(status().isOk());
    }
}
