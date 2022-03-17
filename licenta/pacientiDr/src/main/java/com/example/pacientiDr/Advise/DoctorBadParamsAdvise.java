package com.example.pacientiDr.Advise;

import com.example.pacientiDr.Exception.DoctorExceptionBadParams;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DoctorBadParamsAdvise {

    @ExceptionHandler(DoctorExceptionBadParams.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String doctorBadParamsHandler(DoctorExceptionBadParams ex){
        return ex.getMessage();
    }
}
