package com.example.validator.controller;

import com.example.validator.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/validate")
    public ResponseEntity<Map<String,Object>> validateInput(@RequestBody Map<String,Object> input) {
        Map<String, Object> errors = new HashMap<>();

        // validate each input field
        input.forEach((key, value) -> {
            if (!validatorService.validateFields(key, value.toString())) {
                errors.put(key, "Invalid Field Format: " + value.toString());
            }
        });

        if (!errors.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return ResponseEntity.ok(input);
    }

}
