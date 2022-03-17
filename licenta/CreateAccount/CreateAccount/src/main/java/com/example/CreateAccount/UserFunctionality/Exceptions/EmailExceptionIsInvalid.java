package com.example.CreateAccount.UserFunctionality.Exceptions;

public class EmailExceptionIsInvalid extends  RuntimeException {

    public EmailExceptionIsInvalid(String email){
        super("Email " +  email + " is invalid");
    }

}
