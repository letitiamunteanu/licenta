package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.PatientExceptionAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientAlreadyExistsAdvise {

    @ExceptionHandler(PatientExceptionAlreadyExists.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String patientAlreadyExistsHandler(PatientExceptionAlreadyExists ex){
        return ex.getMessage();
    }
}
