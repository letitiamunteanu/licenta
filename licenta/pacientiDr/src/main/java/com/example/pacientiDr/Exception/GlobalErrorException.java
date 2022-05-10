package com.example.pacientiDr.Exception;

public class GlobalErrorException extends RuntimeException{

    public GlobalErrorException(String message){
        super(message);
    }
}
