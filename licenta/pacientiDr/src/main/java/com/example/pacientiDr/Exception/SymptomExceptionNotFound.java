package com.example.pacientiDr.Exception;

public class SymptomExceptionNotFound extends RuntimeException{

    public SymptomExceptionNotFound(String name){
        super("The symptom " + name + " does not exist!");
    }
}
