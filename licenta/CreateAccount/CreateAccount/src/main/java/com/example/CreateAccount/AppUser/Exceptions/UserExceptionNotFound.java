package com.example.CreateAccount.AppUser.Exceptions;

public class UserExceptionNotFound extends RuntimeException {

    public UserExceptionNotFound(String username){
        super("Could not find the user " + username);
    }
}
