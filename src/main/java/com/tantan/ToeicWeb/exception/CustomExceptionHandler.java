package com.tantan.ToeicWeb.exception;

import com.tantan.ToeicWeb.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DataResponse> handleCustomException(CustomException ex) {
        DataResponse dataResponse = ex.getDataResponse();
        return new ResponseEntity<>(dataResponse, HttpStatus.valueOf(dataResponse.getStatusCode()));
    }
}
