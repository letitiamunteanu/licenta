package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.SymptomExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SymptomNotFoundAdvise {

    @ExceptionHandler(SymptomExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    String symptomNotFoundHandler(SymptomExceptionNotFound ex){
        return ex.getMessage();
    }


}
