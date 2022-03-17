package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.PatientExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientNotFoundAdvise {

    @ExceptionHandler(PatientExceptionNotFound.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String patientNotFoundHandler(PatientExceptionNotFound ex){
        return ex.getMessage();
    }

}
