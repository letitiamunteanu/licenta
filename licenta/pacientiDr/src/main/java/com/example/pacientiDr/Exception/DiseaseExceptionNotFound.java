package com.example.pacientiDr.Exception;

public class DiseaseExceptionNotFound extends RuntimeException {

    public DiseaseExceptionNotFound(String name){
        super("The disease " + name + " does not exist!");
    }
}
