package com.example.CreateAccount.UserFunctionality.Advises;

import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAlreadyExistsAdvise {

    @ExceptionHandler(UserExceptionAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    String userAlreadyExistsHandler(UserExceptionAlreadyExists ex){
        return ex.getMessage();
    }
}
