package com.example.CreateAccount.AppUser.Exceptions;

public class EmailExceptionIsInvalid extends  RuntimeException{

    public EmailExceptionIsInvalid(String email){
        super("Email " +  email + " is invalid");
    }
}
