package com.example.validator.controller;

import com.example.validator.beans.GlobalProfile;
import com.example.validator.response.ApiResponse;
import com.example.validator.response.SuccessfulResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class AppController {

    @PostMapping("spring-validate")
    public ResponseEntity<ApiResponse<SuccessfulResponse>> testValidation(@RequestBody @Valid GlobalProfile profile){
        SuccessfulResponse response = SuccessfulResponse.builder()
                .message("Validation successful")
                .build();
        return new ResponseEntity<>(new ApiResponse<>(response), HttpStatus.OK);
    }
}
