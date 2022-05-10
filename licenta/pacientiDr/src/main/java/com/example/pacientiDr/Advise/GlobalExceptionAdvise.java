package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.GlobalErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionAdvise {

    @ExceptionHandler(GlobalErrorException.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String globalExceptionHandler(GlobalErrorException ex){
        return ex.getMessage();
    }
}
