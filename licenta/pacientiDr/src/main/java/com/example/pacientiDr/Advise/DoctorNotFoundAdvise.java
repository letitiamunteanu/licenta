package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.DoctorExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class DoctorNotFoundAdvise {

    @ExceptionHandler(DoctorExceptionNotFound.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String doctorNotFoundHandler(DoctorExceptionNotFound ex){
        return ex.getMessage();
    }
}
