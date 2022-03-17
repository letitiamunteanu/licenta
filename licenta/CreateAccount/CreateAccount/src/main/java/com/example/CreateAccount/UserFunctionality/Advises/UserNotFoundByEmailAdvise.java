package com.example.CreateAccount.UserFunctionality.Advises;

import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionNotFoundByEmail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundByEmailAdvise {

    @ExceptionHandler(UserExceptionNotFoundByEmail.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundByEmailHandler(UserExceptionNotFoundByEmail ex){
        return ex.getMessage();
    }
}
