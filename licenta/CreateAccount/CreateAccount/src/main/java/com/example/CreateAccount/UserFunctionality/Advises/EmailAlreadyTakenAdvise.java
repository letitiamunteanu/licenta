package com.example.CreateAccount.UserFunctionality.Advises;

import com.example.CreateAccount.UserFunctionality.Exceptions.EmailExceptionIsInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailAlreadyTakenAdvise {

    @ExceptionHandler(EmailExceptionIsInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    String emailAlreadyTakenHandler(EmailExceptionIsInvalid ex){
        return ex.getMessage();
    }
}
