package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JwtExceptionAdvise {

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    String jwtExceptionHandler(JwtException ex){
        return ex.getMessage();
    }
}
