package com.example.pacientiDr.Exception;

public class PatientExceptionAlreadyExists extends RuntimeException{

    public  PatientExceptionAlreadyExists(String id){
        super("The patient with id " + id + " already exists!");
    }
}
