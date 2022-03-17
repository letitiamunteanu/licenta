package com.example.CreateAccount.UserFunctionality.Advises;

import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundAdvise {

    @ExceptionHandler(UserExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    String userNotFoundHandler(UserExceptionNotFound ex){
        return ex.getMessage();
    }
}
