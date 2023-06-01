package com.example.validator.controller;

import com.example.validator.factory.ValidatorFields;
import com.example.validator.service.ValidatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ValidatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidatorService validatorService;

    @Test
    public void testValidateInput_ValidInput() throws Exception {
        Map<String, Object> input = createValidInput();
        when(validatorService.validateFields(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(input)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(input));
    }

    @Test
    public void testValidateInput_InvalidInput() throws Exception {
        Map<String, Object> input = createInvalidInput();
        when(validatorService.validateFields(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(input)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.First_Name").value("Invalid Field Format: 123-4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.Last_Name").value("Invalid Field Format: 456+7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.Gender").value("Invalid Field Format: ASD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.State").value("Invalid Field Format: 1"));

        verify(validatorService, times(4)).validateFields(anyString(), anyString());

    }

    // Helper methods to create input data and convert to JSON string
    private Map<String, Object> createValidInput() {
        Map<String, Object> input = new HashMap<>(Map.of(
                ValidatorFields.FIRST_NAME, "Diego",
                ValidatorFields.LAST_NAME, "Miglino",
                ValidatorFields.STATE, "ARG",
                ValidatorFields.GENDER, "M"
        ));
        return input;
    }

    private Map<String, Object> createInvalidInput() {
        Map<String, Object> input = new HashMap<>(Map.of(
                ValidatorFields.FIRST_NAME, "123-4",
                ValidatorFields.LAST_NAME, "456+7",
                ValidatorFields.STATE, "1",
                ValidatorFields.GENDER, "ASD"
        ));
        return input;
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
