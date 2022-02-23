package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.DoctorExceptionNotFound;
import com.example.pacientiDr.Exception.DoctorExceptionAlreadyExists;
import com.example.pacientiDr.Exception.DoctorExceptionBadParams;
import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.Repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorsService {

    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorsService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    public Doctors addNewDoctor(Doctors doctor){

        if(doctorsRepository.findById(doctor.getId()).isPresent()){
            throw new DoctorExceptionAlreadyExists(doctor.getId());
        }
        else if(doctor.getId() != null && !doctor.getId().equals("") && doctor.getNume() != null && !doctor.getNume().equals("") &&
                doctor.getPrenume() != null && !doctor.getPrenume().equals("") && doctor.getSpecializare() != null && !doctor.getSpecializare().equals("") &&
                doctor.getSediu() != null && !doctor.getSediu().equals("") && doctor.getSala() != null && !doctor.getSala().equals("")){

            doctorsRepository.save(doctor);
            return doctor;
        }
        else {
            throw new DoctorExceptionBadParams("Bad params sent! The doctor was not saved in database!");
        }
    }

    public List<Doctors> getDoctors(){
        return doctorsRepository.findAll();
    }

    public Doctors getDoctorById(String id){

        return doctorsRepository.findById(id).orElseThrow(() -> new DoctorExceptionNotFound(id));
    }

    public List<Doctors> getDoctorByName(String name){

        return doctorsRepository.findAll().stream().filter(doctor -> doctor.getNume().equals(name)).collect(Collectors.toList());
    }

    public List<Doctors> getDoctorBySpecialization(String specialization){

        return doctorsRepository.findAll().stream().filter(doctor -> doctor.getSpecializare().equals(specialization)).collect(Collectors.toList());
    }

    public Doctors updateDoctor(String id, Doctors doctorToUpdate){

        return doctorsRepository.findById(id).map(doctor -> {

            doctor.setNume(doctorToUpdate.getNume());
            doctor.setPrenume(doctorToUpdate.getPrenume());
            doctor.setSpecializare(doctorToUpdate.getSpecializare());
            doctor.setSediu(doctorToUpdate.getSediu());
            doctor.setSala(doctorToUpdate.getSala());

            return doctorsRepository.save(doctorToUpdate);
        }).orElseGet(() -> {
            doctorToUpdate.setId(id);
            return doctorsRepository.save(doctorToUpdate);
        });
    }

    public void deleteDoctor(String id){

        if(doctorsRepository.findById(id).isPresent()){
            doctorsRepository.delete(doctorsRepository.getById(id));
        }
        else{
            throw new DoctorExceptionNotFound(id);
        }
    }
}
