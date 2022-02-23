package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.PatientExceptionAlreadyExists;
import com.example.pacientiDr.Exception.PatientExceptionBadParams;
import com.example.pacientiDr.Exception.PatientExceptionNotFound;
import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatientsService {

    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public Patients addPatient(Patients patient){

        if(patientsRepository.findById(patient.getId()).isPresent()){
            throw new PatientExceptionAlreadyExists(patient.getId());
        }
        else if(!Objects.equals(patient.getId(), "") && patient.getId() != null && patient.getNume() != null && patient.getPrenume() != null && patient.getVarsta() != null
                && patient.getGreutate() != null && patient.getInaltime() != null && patient.getNrTelefon() != null
                && patient.getGrupaSange() != null && patient.getCnp() != null && patient.getDataNastere() != null){

            patientsRepository.save(patient);
            return patient;
        }
        else{
            throw new PatientExceptionBadParams("Bad params sent! The patient was not saved in database!");
        }
    }

    public Patients getPatientById(String id){

        return patientsRepository.findById(id).orElseThrow(() -> new PatientExceptionNotFound(id));
    }

    public List<Patients> getAllPatients(){

        return patientsRepository.findAll();
    }

    public List<Patients> getPatientByName(String name){

        return patientsRepository.findAll().stream().filter(pacient -> pacient.getNume().equals(name)).collect(Collectors.toList());
    }

    public Patients getPatientByCnp(String cnp){

        return patientsRepository.findByCnpContaining(cnp);
    }

    public Patients update(String id, Patients patientToUpdate){

        return patientsRepository.findById(id).map(p -> {

            p.setNume(patientToUpdate.getNume());
            p.setPrenume(patientToUpdate.getPrenume());
            p.setCnp(patientToUpdate.getCnp());
            p.setDataNastere(patientToUpdate.getDataNastere());
            p.setVarsta(patientToUpdate.getVarsta());
            p.setInaltime(patientToUpdate.getInaltime());
            p.setGreutate(patientToUpdate.getGreutate());
            p.setNrTelefon(patientToUpdate.getNrTelefon());
            p.setGrupaSange(patientToUpdate.getGrupaSange());
            p.setSimptome(patientToUpdate.getSimptome());
            p.setSpecificatii(patientToUpdate.getSpecificatii());

            return patientsRepository.save(patientToUpdate);
        }).orElseGet(() -> {
            patientToUpdate.setId(id);
            return patientsRepository.save(patientToUpdate);
        });
    }

    public void delete(String id){

        if(patientsRepository.findById(id).isPresent()){
            patientsRepository.delete(patientsRepository.getById(id));
        }
        else{
            throw new PatientExceptionNotFound(id);
        }
    }


}
