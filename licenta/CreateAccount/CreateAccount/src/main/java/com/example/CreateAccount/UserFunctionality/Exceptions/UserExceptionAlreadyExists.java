package com.example.CreateAccount.UserFunctionality.Exceptions;

public class UserExceptionAlreadyExists extends RuntimeException {

    public UserExceptionAlreadyExists(String username){
        super("User " + username + " already exists!");
    }

}
