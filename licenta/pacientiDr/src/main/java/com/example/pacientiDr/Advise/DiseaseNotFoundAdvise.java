package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.DiseaseExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DiseaseNotFoundAdvise {

    @ExceptionHandler(DiseaseExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    String diseaseNotFoundHandler(DiseaseExceptionNotFound ex){
        return ex.getMessage();
    }
}
