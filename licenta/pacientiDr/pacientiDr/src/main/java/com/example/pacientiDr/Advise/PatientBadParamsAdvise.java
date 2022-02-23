package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.PatientExceptionBadParams;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientBadParamsAdvise {

    @ExceptionHandler(PatientExceptionBadParams.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    String patientBadParamsHandler(PatientExceptionBadParams ex){
        return ex.getMessage();
    }
}
