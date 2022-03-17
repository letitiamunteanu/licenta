package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.DoctorExceptionAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DoctorAlreadyExistsAdvise {

    @ExceptionHandler(DoctorExceptionAlreadyExists.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String doctorAlreadyExistsHandler(DoctorExceptionAlreadyExists ex){
        return ex.getMessage();
    }
}
