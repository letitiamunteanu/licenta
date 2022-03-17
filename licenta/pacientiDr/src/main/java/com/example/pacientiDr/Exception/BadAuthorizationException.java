package com.example.pacientiDr.Exception;


public class BadAuthorizationException extends RuntimeException {

    public BadAuthorizationException(String message) {
        super("You don't have the authority to perform this request! " + message);
    }
}
