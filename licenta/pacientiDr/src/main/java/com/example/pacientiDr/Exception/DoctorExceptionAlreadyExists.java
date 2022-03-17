package com.example.pacientiDr.Exception;

public class DoctorExceptionAlreadyExists extends RuntimeException {

    public DoctorExceptionAlreadyExists(String cuim){
        super("The doctor with cuim " + cuim + " already exists!");
    }
}
