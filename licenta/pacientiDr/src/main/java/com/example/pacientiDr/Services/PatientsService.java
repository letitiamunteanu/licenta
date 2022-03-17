package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.*;
import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Repository.PatientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@Slf4j
@Service
public class PatientsService {

    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public Patients addPatient(Patients patient, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                if(patientsRepository.findById(patient.getId()).isPresent()){
                    throw new PatientExceptionAlreadyExists(patient.getId());
                }
                else if(!Objects.equals(patient.getId(), "") && patient.getId() != null && patient.getNume() != null && patient.getPrenume() != null && patient.getVarsta() != null
                        && patient.getGreutate() != null && patient.getInaltime() != null && patient.getNrTelefon() != null
                        && patient.getGrupaSange() != null && patient.getCnp() != null && patient.getDataNastere() != null){

                    patientsRepository.save(patient);
                    return patient;
                }
                else {
                    throw new PatientExceptionBadParams("Bad params sent! The patient was not saved in database!");
                }
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public Patients getPatientById(String id, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findById(id).orElseThrow(() -> new PatientExceptionNotFound(id));
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public List<Patients> getAllPatients(String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findAll();
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public List<Patients> getPatientByName(String name, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findAll().stream().filter(pacient -> pacient.getNume().equals(name)).collect(Collectors.toList());
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public Patients getPatientByCnp(String cnp, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findByCnpContaining(cnp);
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public Patients update(String id, Patients patientToUpdate, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
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
            } else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }

    public void delete(String id, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                if (patientsRepository.findById(id).isPresent()) {
                    patientsRepository.delete(patientsRepository.getById(id));
                } else {
                    throw new PatientExceptionNotFound(id);
                }
            }
            else {
                throw new BadAuthorizationException("You are a patient");
            }
        }
    }
}
