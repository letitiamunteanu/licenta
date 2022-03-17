package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.SymptomExceptionAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SymptomAlreadyExistsAdvise {

    @ExceptionHandler(SymptomExceptionAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    String symptomAlreadyExistsHandler(SymptomExceptionAlreadyExists ex){
        return ex.getMessage();
    }
}
