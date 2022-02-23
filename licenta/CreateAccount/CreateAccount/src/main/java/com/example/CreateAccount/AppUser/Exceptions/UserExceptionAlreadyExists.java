package com.example.CreateAccount.AppUser.Exceptions;

public class UserExceptionAlreadyExists extends RuntimeException {

    public UserExceptionAlreadyExists(String username){
        super("User " + username + " already exists!");
    }

}
