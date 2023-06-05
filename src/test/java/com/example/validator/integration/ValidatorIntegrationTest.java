package com.example.validator.integration;

import com.example.validator.factory.ValidatorFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidatorIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidateEndpoint_ValidInput() {
        String url = "http://localhost:" + port + "/validate";
        String requestBody = "{\"First_Name\": \"Diego\", \"Last_Name\": \"Miglino\", \"Gender\": \"M\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        try {
            String responseBody = response.getBody();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});

            assertFalse(responseMap.containsKey("errors"));
            assertTrue(responseMap.containsKey(ValidatorFields.FIRST_NAME));
            assertTrue(responseMap.containsKey(ValidatorFields.LAST_NAME));
            assertTrue(responseMap.containsKey(ValidatorFields.GENDER));

            String first = (String) responseMap.get(ValidatorFields.FIRST_NAME);
            assertEquals(first, "Diego");
            String last = (String) responseMap.get(ValidatorFields.LAST_NAME);
            assertEquals(last, "Miglino");
            String gender = (String) responseMap.get(ValidatorFields.GENDER);
            assertEquals(gender, "M");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void invalidFullNameTest() {
        String url = "http://localhost:" + port + "/validate";
        String requestBody = "{\"First_Name\": \"1234\", \"Last_Name\": \"5678\", \"Gender\": \"M\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        try {
            String responseBody = response.getBody();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            System.out.println(responseMap);
            assertTrue(responseMap.containsKey("errors"));
            Map errors = (Map) responseMap.get("errors");

            assertTrue(errors.containsKey(ValidatorFields.FIRST_NAME));
            String nameError = (String) errors.get(ValidatorFields.FIRST_NAME);
            assertEquals(nameError, "Invalid Field Format: 1234");

            assertTrue(errors.containsKey(ValidatorFields.LAST_NAME));
            nameError = (String) errors.get(ValidatorFields.LAST_NAME);
            assertEquals(nameError, "Invalid Field Format: 5678");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void invalidGenderNameTest() {
        String url = "http://localhost:" + port + "/validate";
        String requestBody = "{\"First_Name\": \"Diego\", \"Last_Name\": \"Miglino\", \"Gender\": \"X\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        try {
            String responseBody = response.getBody();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            System.out.println(responseMap);
            assertTrue(responseMap.containsKey("errors"));
            Map errors = (Map) responseMap.get("errors");

            assertFalse(errors.containsKey(ValidatorFields.FIRST_NAME));
            assertFalse(errors.containsKey(ValidatorFields.LAST_NAME));
            assertTrue(errors.containsKey(ValidatorFields.GENDER));
            String gender = (String) errors.get(ValidatorFields.GENDER);
            assertEquals(gender, "Invalid Field Format: X");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
