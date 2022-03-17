package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.BadAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadAuthorizationAdvise {

    @ExceptionHandler(BadAuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody()
    String badAuthorizationHandler(BadAuthorizationException ex){
        return ex.getMessage();
    }
}
