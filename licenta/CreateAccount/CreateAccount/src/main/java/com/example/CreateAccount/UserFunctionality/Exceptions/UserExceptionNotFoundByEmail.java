package com.example.CreateAccount.UserFunctionality.Exceptions;

public class UserExceptionNotFoundByEmail extends RuntimeException{

    public UserExceptionNotFoundByEmail(String email){
        super("Could not find the user with email " + email);
    }

}
