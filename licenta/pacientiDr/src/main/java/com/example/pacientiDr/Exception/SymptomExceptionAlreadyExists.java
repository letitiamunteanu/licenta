package com.example.pacientiDr.Exception;

public class SymptomExceptionAlreadyExists extends RuntimeException {

    public SymptomExceptionAlreadyExists(String name){
        super("The symptom " + name + " already exists!");
    }
}
