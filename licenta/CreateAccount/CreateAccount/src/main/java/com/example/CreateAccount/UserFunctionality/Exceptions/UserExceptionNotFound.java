package com.example.CreateAccount.UserFunctionality.Exceptions;

public class UserExceptionNotFound extends RuntimeException {

    public UserExceptionNotFound(String username){
        super("Could not find the user " + username);
    }

}
