package com.example.pacientiDr.Exception;

public class JwtException extends RuntimeException {

    public JwtException(String message){
        super("Your jwt is " + message);
    }
}
