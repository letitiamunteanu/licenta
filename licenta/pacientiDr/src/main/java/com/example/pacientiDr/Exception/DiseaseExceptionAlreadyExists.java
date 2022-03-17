package com.example.pacientiDr.Exception;


public class DiseaseExceptionAlreadyExists extends RuntimeException {

    public DiseaseExceptionAlreadyExists(String name){
        super("The desease " + name + " already exists!");
    }
}
