package com.example.validator.exception;

import com.example.validator.response.ApiResponse;
import com.example.validator.response.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Spring validation failed");
        BindingResult bindingResult = ex.getBindingResult();
        final List<ObjectError> objectErrors = bindingResult.getAllErrors();
        List<ErrorInfo> errors = new ArrayList<>();
        for(ObjectError objectError : objectErrors){
            ErrorInfo errorInfo = ErrorInfo.builder()
                    .errorId("")
                    .errorCode(objectError.getCode())
                    .errorDesc(objectError.getDefaultMessage())
                    .build();
            errors.add(errorInfo);
        }
        ApiResponse<?> apiResponse = ApiResponse.getApiResponseError(errors, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
