package com.example.pacientiDr.Exception;

public class DoctorExceptionNotFound extends RuntimeException{

    public DoctorExceptionNotFound(String cuim){
        super("Could not found the doctor with cuim " + cuim);
    }
}
